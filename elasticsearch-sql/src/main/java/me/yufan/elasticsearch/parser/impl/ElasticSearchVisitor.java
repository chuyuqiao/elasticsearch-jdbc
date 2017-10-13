package me.yufan.elasticsearch.parser.impl;

import me.yufan.elasticsearch.common.logging.Logger;
import me.yufan.elasticsearch.common.logging.LoggerFactory;
import me.yufan.elasticsearch.common.utils.CollectionUtils;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.Operand;
import me.yufan.elasticsearch.model.logic.*;
import me.yufan.elasticsearch.model.operands.LimitOperand;
import me.yufan.elasticsearch.model.operands.OrderByOperand;
import me.yufan.elasticsearch.model.operands.binary.BinaryOperand;
import me.yufan.elasticsearch.model.operands.column.AliasOperand;
import me.yufan.elasticsearch.model.operands.column.DistinctOperand;
import me.yufan.elasticsearch.model.operands.enums.BinaryType;
import me.yufan.elasticsearch.model.operands.function.FunctionFactory;
import me.yufan.elasticsearch.model.operands.function.FunctionOperand;
import me.yufan.elasticsearch.model.operands.primitive.*;
import me.yufan.elasticsearch.parser.ElasticSearchParser;
import me.yufan.elasticsearch.parser.ElasticSearchParser.*;
import me.yufan.elasticsearch.parser.ElasticSearchParserVisitor;
import me.yufan.elasticsearch.parser.ParserResult;
import me.yufan.elasticsearch.parser.SQLTemplate;
import me.yufan.elasticsearch.parser.exception.ParseException;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

@SuppressWarnings("unchecked")
public class ElasticSearchVisitor implements ElasticSearchParserVisitor<ParserResult> {

    private static final Logger log = LoggerFactory.getLog(ElasticSearchVisitor.class);

    // A temp space to hold the parser tree, because we use DFS.
    private Deque<Object> parserHolder = new ArrayDeque<>();

    // SELECT name part, this field would be the back up for process cycle in case of missing field in column table name
    private String defaultTableName;

    private SQLTemplate template = new SQLTemplate();

    @Override
    public ParserResult visitProg(ProgContext ctx) {
        if (ctx.selectOperation() != null) {
            return visitSelectOperation(ctx.selectOperation());
        } else if (ctx.deleteOperation() != null) {
            return visitDeleteOperation(ctx.deleteOperation());
        }
        return ParserResult.failed("AST tree has some extra grammar module that program couldn't transform.");
    }

    @Override
    public ParserResult visitSelectOperation(SelectOperationContext ctx) {
        if (ctx.tableRef() != null) {
            ParserResult tableRef = visitTableRef(ctx.tableRef());
            if (tableRef.isSuccess()) {
                template.setTable((String) parserHolder.pop());
            } else {
                return tableRef;
            }
        }
        if (ctx.columnList() != null) {
            ParserResult columnList = visitColumnList(ctx.columnList());
            if (columnList.isSuccess()) {
                template.setColumns((List<Operand>) parserHolder.pop());
            } else {
                return columnList;
            }
        }
        if (ctx.whereClause() != null) {
            ParserResult whereClause = visitWhereClause(ctx.whereClause());
            if (whereClause.isSuccess()) {
                template.setWhereClause((BooleanExpr) parserHolder.pop());
            } else {
                return whereClause;
            }
        }
        if (ctx.groupClause() != null) {
            ParserResult groupClause = visitGroupClause(ctx.groupClause());
            if (groupClause.isSuccess()) {
                template.setGroupBys((List<Operand>) parserHolder.pop());
            } else {
                return groupClause;
            }
        }
        if (ctx.orderClause() != null) {
            ParserResult orderClause = visitOrderClause(ctx.orderClause());
            if (orderClause.isSuccess()) {
                template.setOrderBy((List<OrderByOperand>) parserHolder.pop());
            } else {
                return orderClause;
            }
        }
        if (ctx.limitClause() != null) {
            ParserResult limitClause = visitLimitClause(ctx.limitClause());
            if (limitClause.isSuccess()) {
                template.setLimit((LimitOperand) parserHolder.pop());
            } else {
                return limitClause;
            }
        }
        return ParserResult.success(template);
    }

    @Override
    public ParserResult visitDeleteOperation(DeleteOperationContext ctx) {
        throw new UnsupportedOperationException("DELETE is not supported currently.");
    }

    @Override
    public ParserResult visitColumnList(ColumnListContext ctx) {
        List<NameOperandContext> nameOperands = ctx.nameOperand();
        if (CollectionUtils.isEmpty(nameOperands)) {
            return ParserResult.failed("Missing need select column");
        } else {
            List<Operand> operands = new ArrayList<>(nameOperands.size());
            for (NameOperandContext operand : nameOperands) {
                ParserResult result = visitNameOperand(operand);
                if (!result.isSuccess()) {
                    return result;
                }
                Operand op = (Operand) parserHolder.pop();
                operands.add(op);
            }
            parserHolder.push(operands);
            return ParserResult.success();
        }
    }

    @Override
    public ParserResult visitNameOperand(NameOperandContext ctx) {
        // Back up table for further restore
        parserHolder.push(defaultTableName);
        if (ctx.tableName != null) {
            String tableNameText = ctx.tableName.getText();
            if (!Objects.equals(tableNameText, defaultTableName)) {
                log.warn("The select table name " + defaultTableName + " is not equal with column table name " + tableNameText);
            }
            defaultTableName = tableNameText; // This would be used in sub-name parser process (one day ?)
        }

        ParserResult name = visitName(ctx.columName);
        if (name.isSuccess()) {
            Operand innerOperand = (Operand) parserHolder.pop();
            defaultTableName = (String) parserHolder.pop();

            if (ctx.alias != null) {
                parserHolder.push(new AliasOperand(innerOperand, ctx.alias.getText()));
            } else {
                parserHolder.push(innerOperand);
            }
            return ParserResult.success();
        } else {
            return name;
        }
    }

    // Helper method for dispatch visit to different name field
    private ParserResult visitName(NameContext ctx) {
        if (ctx instanceof LRNameContext) {
            return visitLRName((LRNameContext) ctx);
        } else if (ctx instanceof DistinctContext) {
            return visitDistinct((DistinctContext) ctx);
        } else if (ctx instanceof BinaryNameContext) {
            return visitBinaryName((BinaryNameContext) ctx);
        } else if (ctx instanceof AggregationNameContext) {
            return visitAggregationName((AggregationNameContext) ctx);
        } else if (ctx instanceof ColumnNameContext) {
            return visitColumnName((ColumnNameContext) ctx);
        }
        return ParserResult.failed("The name type is not a supported class " + ctx.getClass().getName());
    }

    @Override
    public ParserResult visitBinaryName(BinaryNameContext ctx) {
        ParserResult left = visitName(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        Operand leftOp = (Operand) parserHolder.pop();
        ParserResult right = visitName(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        Operand rightOp = (Operand) parserHolder.pop();

        try {
            BinaryOperand binaryOp = BinaryType.newInstance(ctx.op, leftOp, rightOp);
            parserHolder.push(binaryOp);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            return ParserResult.failed(e.getMessage());
        }
        return ParserResult.success();
    }

    @Override
    public ParserResult visitAggregationName(AggregationNameContext ctx) {
        String functionId = ctx.ID().getText();
        ParserResult collection = visitCollection(ctx.collection());
        if (!collection.isSuccess()) {
            return collection;
        } else {
            List<PrimitiveOperand> operands = (List<PrimitiveOperand>) parserHolder.pop();
            try {
                FunctionOperand functionOperand = FunctionFactory.newInstance(functionId, operands);
                parserHolder.push(functionOperand);
            } catch (ParseException e) {
                log.error(e.getMessage(), e);
                return ParserResult.failed(e.getMessage());
            }
            return ParserResult.success();
        }
    }

    @Override
    public ParserResult visitLRName(LRNameContext ctx) {
        // (name) format
        return visitName(ctx.name());
    }

    @Override
    public ParserResult visitDistinct(DistinctContext ctx) {
        ParserResult visitName = visitName(ctx.name());
        if (!visitName.isSuccess()) {
            return visitName;
        } else {
            Operand nameOp = (Operand) parserHolder.pop();
            DistinctOperand operand = new DistinctOperand(nameOp);
            parserHolder.push(operand);
            return ParserResult.success();
        }
    }

    @Override
    public ParserResult visitColumnName(ColumnNameContext ctx) {
        IdentityContext identity = ctx.identity();
        return visitIdentity(identity);
    }

    @Override
    public ParserResult visitIdEle(IdEleContext ctx) {
        parserHolder.push(new NameOperand(defaultTableName, ctx.ID().getText()));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitIntEle(IntEleContext ctx) {
        parserHolder.push(new IntPrimitiveOperand(ctx.INT().getText()));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitFloatEle(FloatEleContext ctx) {
        parserHolder.push(new FloatPrimitiveOperand(ctx.FLOAT().getText()));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitStringEle(StringEleContext ctx) {
        parserHolder.push(new StringPrimitiveOperand(ctx.STRING().getText()));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitNameOpr(NameOprContext ctx) {
        return visitName(ctx.name());
    }

    @Override
    public ParserResult visitGtOpr(GtOprContext ctx) {
        ParserResult left = visitBoolExpr(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        Operand lop = (Operand) parserHolder.pop();

        ParserResult right = visitBoolExpr(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        Operand rop = (Operand) parserHolder.pop();

        parserHolder.push(new BooleanExprGt(lop, rop, false));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitEqOpr(EqOprContext ctx) {
        ParserResult left = visitBoolExpr(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        Operand lop = (Operand) parserHolder.pop();

        ParserResult right = visitBoolExpr(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        Operand rop = (Operand) parserHolder.pop();

        parserHolder.push(new BooleanExprEq(lop, rop));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitLrExpr(LrExprContext ctx) {
        return visitBoolExpr(ctx.boolExpr());
    }

    @Override
    public ParserResult visitAndOpr(AndOprContext ctx) {
        ParserResult left = visitBoolExpr(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        BooleanExpr lop = (BooleanExpr) parserHolder.pop();

        ParserResult right = visitBoolExpr(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        BooleanExpr rop = (BooleanExpr) parserHolder.pop();

        parserHolder.push(new BooleanExprAnd(lop, rop));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitLteqOpr(LteqOprContext ctx) {
        ParserResult left = visitBoolExpr(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        Operand lop = (Operand) parserHolder.pop();

        ParserResult right = visitBoolExpr(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        Operand rop = (Operand) parserHolder.pop();

        parserHolder.push(new BooleanExprLt(lop, rop, true));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitNotEqOpr(NotEqOprContext ctx) {
        ParserResult left = visitBoolExpr(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        Operand lop = (Operand) parserHolder.pop();

        ParserResult right = visitBoolExpr(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        Operand rop = (Operand) parserHolder.pop();

        parserHolder.push(new BooleanExprNot(new BooleanExprEq(lop, rop)));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitInBooleanExpr(InBooleanExprContext ctx) {
        return visitInExpr(ctx.inExpr());
    }

    @Override
    public ParserResult visitLtOpr(LtOprContext ctx) {
        ParserResult left = visitBoolExpr(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        Operand lop = (Operand) parserHolder.pop();

        ParserResult right = visitBoolExpr(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        Operand rop = (Operand) parserHolder.pop();

        parserHolder.push(new BooleanExprLt(lop, rop, false));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitGteqOpr(GteqOprContext ctx) {
        ParserResult left = visitBoolExpr(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        Operand lop = (Operand) parserHolder.pop();

        ParserResult right = visitBoolExpr(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        Operand rop = (Operand) parserHolder.pop();

        parserHolder.push(new BooleanExprGt(lop, rop, true));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitOrOpr(OrOprContext ctx) {
        ParserResult left = visitBoolExpr(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        BooleanExpr lop = (BooleanExpr) parserHolder.pop();

        ParserResult right = visitBoolExpr(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        BooleanExpr rop = (BooleanExpr) parserHolder.pop();

        parserHolder.push(new BooleanExprOr(Arrays.asList(lop, rop)));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitBetweenExpr(BetweenExprContext ctx) {
        ParserResult left = visitBoolExpr(ctx.left);
        if (!left.isSuccess()) {
            return left;
        }
        Operand lop = (Operand) parserHolder.pop();

        ParserResult right = visitBoolExpr(ctx.right);
        if (!right.isSuccess()) {
            return right;
        }
        Operand rop = (Operand) parserHolder.pop();

        parserHolder.push(new BooleanExprBetween(lop, rop));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitInExpr(InExprContext ctx) {
        ParserResult id = visitIdentity(ctx.left);
        if (!id.isSuccess()) {
            return id;
        }
        Operand left = (Operand) parserHolder.pop();

        ParserResult list = visitInRightOperandList(ctx.right);
        if (!list.isSuccess()) {
            return list;
        }
        List<Operand> operands = (List<Operand>) parserHolder.pop();

        InTokenContext token = ctx.inToken();
        if (token instanceof InOpContext) {
            return visitIn(left, operands, new LinkedList<>());
        } else if (token instanceof NotInOpContext) {
            return visitNotIn(left, operands, new LinkedList<>());
        }

        return ParserResult.failed("No such token " + token.getText() + " was supported");
    }

    private ParserResult visitIn(Operand left, List<Operand> operands, List<BooleanExpr> list) {
        for (Operand operand : operands) {
            list.add(new BooleanExprEq(left, operand));
        }
        BooleanExpr result = foldToBooleanOr(list);
        parserHolder.push(result);
        return ParserResult.success();
    }

    private ParserResult visitNotIn(Operand left, List<Operand> operands, List<BooleanExpr> list) {
        for (Operand operand : operands) {
            list.add(new BooleanExprNot(new BooleanExprEq(left, operand)));
        }
        BooleanExpr result = foldToBooleanAnd(list);
        parserHolder.push(result);
        return ParserResult.success();
    }

    private BooleanExpr foldToBooleanAnd(List<BooleanExpr> list) {
        BooleanExpr result = list.remove(0);
        for (BooleanExpr expr : list) {
            result = new BooleanExprAnd(result, expr);
        }
        return result;
    }

    private BooleanExpr foldToBooleanOr(List<BooleanExpr> list) {
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return new BooleanExprOr(list);
        }
    }

    @Override
    public ParserResult visitInOp(InOpContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitNotInOp(NotInOpContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitCollection(CollectionContext ctx) {
        List<ElasticSearchParser.IdentityContext> identities = ctx.identity();
        if (CollectionUtils.isNotEmpty(identities)) {
            List<PrimitiveOperand> operands = new ArrayList<>(identities.size());
            for (IdentityContext identity : identities) {
                ParserResult result = visitIdentity(identity);
                if (result.isSuccess()) {
                    PrimitiveOperand op = (PrimitiveOperand) parserHolder.pop();
                    operands.add(op);
                } else {
                    return result;
                }
            }
            parserHolder.push(operands);
            return ParserResult.success();
        } else {
            return ParserResult.failed("Identities shouldn't be empty");
        }
    }

    private ParserResult visitIdentity(IdentityContext identity) {
        if (identity instanceof IdEleContext) {
            return visitIdEle((IdEleContext) identity);
        } else if (identity instanceof IntEleContext) {
            return visitIntEle((IntEleContext) identity);
        } else if (identity instanceof FloatEleContext) {
            return visitFloatEle((FloatEleContext) identity);
        } else if (identity instanceof StringEleContext) {
            return visitStringEle((StringEleContext) identity);
        }
        return ParserResult.failed("Unsupported identity type " + identity.getClass().getName());
    }

    @Override
    public ParserResult visitInRightOperandList(InRightOperandListContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitConstLiteral(ConstLiteralContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitArithmeticLiteral(ArithmeticLiteralContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitTableRef(TableRefContext ctx) {
        defaultTableName = ctx.getText();
        parserHolder.push(defaultTableName); // Table alias is not supported currently
        log.debug("The table name is " + defaultTableName);
        return ParserResult.success();
    }

    @Override
    public ParserResult visitWhereClause(WhereClauseContext ctx) {
        BoolExprContext boolExpr = ctx.boolExpr();
        return visitBoolExpr(boolExpr);
    }

    private ParserResult visitBoolExpr(BoolExprContext ctx) {
        if (ctx instanceof LrExprContext) {
            return visitLrExpr((LrExprContext) ctx);
        } else if (ctx instanceof EqOprContext) {
            return visitEqOpr((EqOprContext) ctx);
        } else if (ctx instanceof GtOprContext) {
            return visitGtOpr((GtOprContext) ctx);
        } else if (ctx instanceof LtOprContext) {
            return visitLtOpr((LtOprContext) ctx);
        } else if (ctx instanceof GteqOprContext) {
            return visitGteqOpr((GteqOprContext) ctx);
        } else if (ctx instanceof LteqOprContext) {
            return visitLteqOpr((LteqOprContext) ctx);
        } else if (ctx instanceof NotEqOprContext) {
            return visitNotEqOpr((NotEqOprContext) ctx);
        } else if (ctx instanceof AndOprContext) {
            return visitAndOpr((AndOprContext) ctx);
        } else if (ctx instanceof OrOprContext) {
            return visitOrOpr((OrOprContext) ctx);
        } else if (ctx instanceof BetweenExprContext) {
            return visitBetweenExpr((BetweenExprContext) ctx);
        } else if (ctx instanceof InBooleanExprContext) {
            return visitInBooleanExpr((InBooleanExprContext) ctx);
        } else if (ctx instanceof NameOprContext) {
            return visitNameOpr((NameOprContext) ctx);
        }
        return ParserResult.failed("No such bool expr supported.");
    }

    @Override
    public ParserResult visitGroupClause(GroupClauseContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitOrderClause(OrderClauseContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitOrder(OrderContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitLimitClause(LimitClauseContext ctx) {
        return null;
    }

    @Override
    public ParserResult visit(ParseTree tree) {
        if (tree instanceof ProgContext) {
            return visitProg((ProgContext) tree);
        }
        return ParserResult.failed("The antlr4 parsed entry point is not prog, check your g4 syntax");
    }

    @Override
    public ParserResult visitChildren(RuleNode node) {
        throw new UnsupportedOperationException("No children visitor implemented");
    }

    @Override
    public ParserResult visitTerminal(TerminalNode node) {
        throw new UnsupportedOperationException("No terminal visitor implemented");
    }

    @Override
    public ParserResult visitErrorNode(ErrorNode node) {
        throw new UnsupportedOperationException("Error would be thrown by ThrowingExceptionErrorStrategy");
    }
}

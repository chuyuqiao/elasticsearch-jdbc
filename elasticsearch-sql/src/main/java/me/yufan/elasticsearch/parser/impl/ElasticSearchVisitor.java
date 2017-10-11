package me.yufan.elasticsearch.parser.impl;

import me.yufan.elasticsearch.common.logging.Logger;
import me.yufan.elasticsearch.common.logging.LoggerFactory;
import me.yufan.elasticsearch.common.utils.CollectionUtils;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.Operand;
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
    public ParserResult visitProg(ElasticSearchParser.ProgContext ctx) {
        if (ctx.selectOperation() != null) {
            return visitSelectOperation(ctx.selectOperation());
        } else if (ctx.deleteOperation() != null) {
            return visitDeleteOperation(ctx.deleteOperation());
        }
        return ParserResult.failed("AST tree has some extra grammar module that program couldn't transform.");
    }

    @Override
    public ParserResult visitSelectOperation(ElasticSearchParser.SelectOperationContext ctx) {
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
    public ParserResult visitDeleteOperation(ElasticSearchParser.DeleteOperationContext ctx) {
        throw new UnsupportedOperationException("DELETE is not supported currently.");
    }

    @Override
    public ParserResult visitColumnList(ElasticSearchParser.ColumnListContext ctx) {
        List<ElasticSearchParser.NameOperandContext> nameOperands = ctx.nameOperand();
        if (CollectionUtils.isEmpty(nameOperands)) {
            return ParserResult.failed("Missing need select column");
        } else {
            List<Operand> operands = new ArrayList<>(nameOperands.size());
            for (ElasticSearchParser.NameOperandContext operand : nameOperands) {
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
    public ParserResult visitNameOperand(ElasticSearchParser.NameOperandContext ctx) {
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
    private ParserResult visitName(ElasticSearchParser.NameContext ctx) {
        if (ctx instanceof ElasticSearchParser.LRNameContext) {
            return visitLRName((ElasticSearchParser.LRNameContext) ctx);
        } else if (ctx instanceof ElasticSearchParser.DistinctContext) {
            return visitDistinct((ElasticSearchParser.DistinctContext) ctx);
        } else if (ctx instanceof ElasticSearchParser.BinaryNameContext) {
            return visitBinaryName((ElasticSearchParser.BinaryNameContext) ctx);
        } else if (ctx instanceof ElasticSearchParser.AggregationNameContext) {
            return visitAggregationName((ElasticSearchParser.AggregationNameContext) ctx);
        } else if (ctx instanceof ElasticSearchParser.ColumnNameContext) {
            return visitColumnName((ElasticSearchParser.ColumnNameContext) ctx);
        }
        return ParserResult.failed("The name type is not a supported class " + ctx.getClass().getName());
    }

    @Override
    public ParserResult visitBinaryName(ElasticSearchParser.BinaryNameContext ctx) {
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
    public ParserResult visitAggregationName(ElasticSearchParser.AggregationNameContext ctx) {
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
    public ParserResult visitLRName(ElasticSearchParser.LRNameContext ctx) {
        // (name) format
        return visitName(ctx.name());
    }

    @Override
    public ParserResult visitDistinct(ElasticSearchParser.DistinctContext ctx) {
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
    public ParserResult visitColumnName(ElasticSearchParser.ColumnNameContext ctx) {
        ElasticSearchParser.IdentityContext identity = ctx.identity();
        return visitIdentity(identity);
    }

    @Override
    public ParserResult visitIdEle(ElasticSearchParser.IdEleContext ctx) {
        parserHolder.push(new NameOperand(defaultTableName, ctx.ID().getText()));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitIntEle(ElasticSearchParser.IntEleContext ctx) {
        parserHolder.push(new IntPrimitiveOperand(ctx.INT().getText()));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitFloatEle(ElasticSearchParser.FloatEleContext ctx) {
        parserHolder.push(new FloatPrimitiveOperand(ctx.FLOAT().getText()));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitStringEle(ElasticSearchParser.StringEleContext ctx) {
        parserHolder.push(new StringPrimitiveOperand(ctx.STRING().getText()));
        return ParserResult.success();
    }

    @Override
    public ParserResult visitNameOpr(ElasticSearchParser.NameOprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitGtOpr(ElasticSearchParser.GtOprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitEqOpr(ElasticSearchParser.EqOprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitLrExpr(ElasticSearchParser.LrExprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitAndOpr(ElasticSearchParser.AndOprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitLteqOpr(ElasticSearchParser.LteqOprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitNotEqOpr(ElasticSearchParser.NotEqOprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitInBooleanExpr(ElasticSearchParser.InBooleanExprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitLtOpr(ElasticSearchParser.LtOprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitGteqOpr(ElasticSearchParser.GteqOprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitOrOpr(ElasticSearchParser.OrOprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitBetweenExpr(ElasticSearchParser.BetweenExprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitInExpr(ElasticSearchParser.InExprContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitInOp(ElasticSearchParser.InOpContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitNotInOp(ElasticSearchParser.NotInOpContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitCollection(ElasticSearchParser.CollectionContext ctx) {
        List<ElasticSearchParser.IdentityContext> identities = ctx.identity();
        if (CollectionUtils.isNotEmpty(identities)) {
            List<PrimitiveOperand> operands = new ArrayList<>(identities.size());
            for (ElasticSearchParser.IdentityContext identity : identities) {
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

    private ParserResult visitIdentity(ElasticSearchParser.IdentityContext identity) {
        if (identity instanceof ElasticSearchParser.IdEleContext) {
            return visitIdEle((ElasticSearchParser.IdEleContext) identity);
        } else if (identity instanceof ElasticSearchParser.IntEleContext) {
            return visitIntEle((ElasticSearchParser.IntEleContext) identity);
        } else if (identity instanceof ElasticSearchParser.FloatEleContext) {
            return visitFloatEle((ElasticSearchParser.FloatEleContext) identity);
        } else if (identity instanceof ElasticSearchParser.StringEleContext) {
            return visitStringEle((ElasticSearchParser.StringEleContext) identity);
        }
        return ParserResult.failed("Unsupported identity type " + identity.getClass().getName());
    }

    @Override
    public ParserResult visitInRightOperandList(ElasticSearchParser.InRightOperandListContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitConstLiteral(ElasticSearchParser.ConstLiteralContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitArithmeticLiteral(ElasticSearchParser.ArithmeticLiteralContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitTableRef(ElasticSearchParser.TableRefContext ctx) {
        defaultTableName = ctx.getText();
        parserHolder.push(defaultTableName); // Table alias is not supported currently
        log.debug("The table name is " + defaultTableName);
        return ParserResult.success();
    }

    @Override
    public ParserResult visitWhereClause(ElasticSearchParser.WhereClauseContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitGroupClause(ElasticSearchParser.GroupClauseContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitOrderClause(ElasticSearchParser.OrderClauseContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitOrder(ElasticSearchParser.OrderContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitLimitClause(ElasticSearchParser.LimitClauseContext ctx) {
        return null;
    }

    @Override
    public ParserResult visit(ParseTree tree) {
        if (tree instanceof ElasticSearchParser.ProgContext) {
            return visitProg((ElasticSearchParser.ProgContext) tree);
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

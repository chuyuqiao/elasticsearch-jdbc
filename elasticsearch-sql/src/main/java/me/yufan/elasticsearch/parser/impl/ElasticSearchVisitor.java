package me.yufan.elasticsearch.parser.impl;

import me.yufan.elasticsearch.common.logging.Logger;
import me.yufan.elasticsearch.common.logging.LoggerFactory;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.Operand;
import me.yufan.elasticsearch.model.operands.LimitOperand;
import me.yufan.elasticsearch.model.operands.OrderByOperand;
import me.yufan.elasticsearch.parser.ElasticSearchParser;
import me.yufan.elasticsearch.parser.ElasticSearchParserVisitor;
import me.yufan.elasticsearch.parser.ParserResult;
import me.yufan.elasticsearch.parser.SQLTemplate;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@SuppressWarnings("unchecked")
public class ElasticSearchVisitor implements ElasticSearchParserVisitor<ParserResult> {

    private static final Logger log = LoggerFactory.getLog(ElasticSearchVisitor.class);

    // A temp space to hold the parser tree, because we use DFS.
    private Deque<Object> parserHolder = new ArrayDeque<>();

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
                template.setTable(String.class.cast(parserHolder.pop()));
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
                template.setWhereClause(BooleanExpr.class.cast(parserHolder.pop()));
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
                template.setLimit(LimitOperand.class.cast(parserHolder.pop()));
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
        return null;
    }

    @Override
    public ParserResult visitNameOperand(ElasticSearchParser.NameOperandContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitMulName(ElasticSearchParser.MulNameContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitAggregationName(ElasticSearchParser.AggregationNameContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitAddName(ElasticSearchParser.AddNameContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitLRName(ElasticSearchParser.LRNameContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitDistinct(ElasticSearchParser.DistinctContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitColumnName(ElasticSearchParser.ColumnNameContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitIdEle(ElasticSearchParser.IdEleContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitIntEle(ElasticSearchParser.IntEleContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitFloatEle(ElasticSearchParser.FloatEleContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitStringEle(ElasticSearchParser.StringEleContext ctx) {
        return null;
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
    public ParserResult visitIntLiteral(ElasticSearchParser.IntLiteralContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitFloatLiteral(ElasticSearchParser.FloatLiteralContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitStringLiteral(ElasticSearchParser.StringLiteralContext ctx) {
        return null;
    }

    @Override
    public ParserResult visitTableRef(ElasticSearchParser.TableRefContext ctx) {
        parserHolder.push(ctx.getText()); // Table alias is not supported currently
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
            return visitProg(ElasticSearchParser.ProgContext.class.cast(tree));
        }
        return ParserResult.failed("The antlr4 parsed entry point is not prog, check your g4 syntax");
    }

    @Override
    public ParserResult visitChildren(RuleNode node) {
        return null;
    }

    @Override
    public ParserResult visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public ParserResult visitErrorNode(ErrorNode node) {
        return null;
    }
}

package me.yufan.elasticsearch.parser.impl;

import me.yufan.elasticsearch.parser.ElasticSearchParser;
import me.yufan.elasticsearch.parser.ElasticSearchParserVisitor;
import me.yufan.elasticsearch.parser.SQLTemplate;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ElasticSearchVisitor implements ElasticSearchParserVisitor<SQLTemplate> {

    @Override
    public SQLTemplate visitProg(ElasticSearchParser.ProgContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitSelectOperation(ElasticSearchParser.SelectOperationContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitDeleteOperation(ElasticSearchParser.DeleteOperationContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitColumList(ElasticSearchParser.ColumListContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitNameOprand(ElasticSearchParser.NameOprandContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitMulName(ElasticSearchParser.MulNameContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitAggregationName(ElasticSearchParser.AggregationNameContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitAddName(ElasticSearchParser.AddNameContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitLRName(ElasticSearchParser.LRNameContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitDistinct(ElasticSearchParser.DistinctContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitColumnName(ElasticSearchParser.ColumnNameContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitIdEle(ElasticSearchParser.IdEleContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitIntEle(ElasticSearchParser.IntEleContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitFloatEle(ElasticSearchParser.FloatEleContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitStringEle(ElasticSearchParser.StringEleContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitNameOpr(ElasticSearchParser.NameOprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitGtOpr(ElasticSearchParser.GtOprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitEqOpr(ElasticSearchParser.EqOprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitLrExpr(ElasticSearchParser.LrExprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitAndOpr(ElasticSearchParser.AndOprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitLteqOpr(ElasticSearchParser.LteqOprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitNotEqOpr(ElasticSearchParser.NotEqOprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitInBooleanExpr(ElasticSearchParser.InBooleanExprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitLtOpr(ElasticSearchParser.LtOprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitGteqOpr(ElasticSearchParser.GteqOprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitOrOpr(ElasticSearchParser.OrOprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitBetweenExpr(ElasticSearchParser.BetweenExprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitInExpr(ElasticSearchParser.InExprContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitInOp(ElasticSearchParser.InOpContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitNotInOp(ElasticSearchParser.NotInOpContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitInRightOprandList(ElasticSearchParser.InRightOprandListContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitConstLiteral(ElasticSearchParser.ConstLiteralContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitArithmeticLiteral(ElasticSearchParser.ArithmeticLiteralContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitIntLiteral(ElasticSearchParser.IntLiteralContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitFloatLiteral(ElasticSearchParser.FloatLiteralContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitStringLiteral(ElasticSearchParser.StringLiteralContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitTableRef(ElasticSearchParser.TableRefContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitWhereClause(ElasticSearchParser.WhereClauseContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitGroupClause(ElasticSearchParser.GroupClauseContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitOrderClause(ElasticSearchParser.OrderClauseContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitOrder(ElasticSearchParser.OrderContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visitLimitClause(ElasticSearchParser.LimitClauseContext ctx) {
        return null;
    }

    @Override
    public SQLTemplate visit(ParseTree tree) {
        return null;
    }

    @Override
    public SQLTemplate visitChildren(RuleNode node) {
        return null;
    }

    @Override
    public SQLTemplate visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public SQLTemplate visitErrorNode(ErrorNode node) {
        return null;
    }
}

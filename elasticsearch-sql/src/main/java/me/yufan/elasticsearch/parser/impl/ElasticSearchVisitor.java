package me.yufan.elasticsearch.parser.impl;

import me.yufan.elasticsearch.parser.ElasticSearchParser;
import me.yufan.elasticsearch.parser.ElasticSearchParserVisitor;
import me.yufan.elasticsearch.parser.Query;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ElasticSearchVisitor implements ElasticSearchParserVisitor<Query> {

    @Override
    public Query visitProg(ElasticSearchParser.ProgContext ctx) {
        return null;
    }

    @Override
    public Query visitSelectOperation(ElasticSearchParser.SelectOperationContext ctx) {
        return null;
    }

    @Override
    public Query visitDeleteOperation(ElasticSearchParser.DeleteOperationContext ctx) {
        return null;
    }

    @Override
    public Query visitColumList(ElasticSearchParser.ColumListContext ctx) {
        return null;
    }

    @Override
    public Query visitNameOprand(ElasticSearchParser.NameOprandContext ctx) {
        return null;
    }

    @Override
    public Query visitMulName(ElasticSearchParser.MulNameContext ctx) {
        return null;
    }

    @Override
    public Query visitAggregationName(ElasticSearchParser.AggregationNameContext ctx) {
        return null;
    }

    @Override
    public Query visitAddName(ElasticSearchParser.AddNameContext ctx) {
        return null;
    }

    @Override
    public Query visitLRName(ElasticSearchParser.LRNameContext ctx) {
        return null;
    }

    @Override
    public Query visitDistinct(ElasticSearchParser.DistinctContext ctx) {
        return null;
    }

    @Override
    public Query visitColumnName(ElasticSearchParser.ColumnNameContext ctx) {
        return null;
    }

    @Override
    public Query visitIdEle(ElasticSearchParser.IdEleContext ctx) {
        return null;
    }

    @Override
    public Query visitIntEle(ElasticSearchParser.IntEleContext ctx) {
        return null;
    }

    @Override
    public Query visitFloatEle(ElasticSearchParser.FloatEleContext ctx) {
        return null;
    }

    @Override
    public Query visitStringEle(ElasticSearchParser.StringEleContext ctx) {
        return null;
    }

    @Override
    public Query visitNameOpr(ElasticSearchParser.NameOprContext ctx) {
        return null;
    }

    @Override
    public Query visitGtOpr(ElasticSearchParser.GtOprContext ctx) {
        return null;
    }

    @Override
    public Query visitEqOpr(ElasticSearchParser.EqOprContext ctx) {
        return null;
    }

    @Override
    public Query visitLrExpr(ElasticSearchParser.LrExprContext ctx) {
        return null;
    }

    @Override
    public Query visitAndOpr(ElasticSearchParser.AndOprContext ctx) {
        return null;
    }

    @Override
    public Query visitLteqOpr(ElasticSearchParser.LteqOprContext ctx) {
        return null;
    }

    @Override
    public Query visitNotEqOpr(ElasticSearchParser.NotEqOprContext ctx) {
        return null;
    }

    @Override
    public Query visitInBooleanExpr(ElasticSearchParser.InBooleanExprContext ctx) {
        return null;
    }

    @Override
    public Query visitLtOpr(ElasticSearchParser.LtOprContext ctx) {
        return null;
    }

    @Override
    public Query visitGteqOpr(ElasticSearchParser.GteqOprContext ctx) {
        return null;
    }

    @Override
    public Query visitOrOpr(ElasticSearchParser.OrOprContext ctx) {
        return null;
    }

    @Override
    public Query visitBetweenExpr(ElasticSearchParser.BetweenExprContext ctx) {
        return null;
    }

    @Override
    public Query visitInExpr(ElasticSearchParser.InExprContext ctx) {
        return null;
    }

    @Override
    public Query visitInOp(ElasticSearchParser.InOpContext ctx) {
        return null;
    }

    @Override
    public Query visitNotInOp(ElasticSearchParser.NotInOpContext ctx) {
        return null;
    }

    @Override
    public Query visitInRightOprandList(ElasticSearchParser.InRightOprandListContext ctx) {
        return null;
    }

    @Override
    public Query visitConstLiteral(ElasticSearchParser.ConstLiteralContext ctx) {
        return null;
    }

    @Override
    public Query visitArithmeticLiteral(ElasticSearchParser.ArithmeticLiteralContext ctx) {
        return null;
    }

    @Override
    public Query visitIntLiteral(ElasticSearchParser.IntLiteralContext ctx) {
        return null;
    }

    @Override
    public Query visitFloatLiteral(ElasticSearchParser.FloatLiteralContext ctx) {
        return null;
    }

    @Override
    public Query visitStringLiteral(ElasticSearchParser.StringLiteralContext ctx) {
        return null;
    }

    @Override
    public Query visitTableRef(ElasticSearchParser.TableRefContext ctx) {
        return null;
    }

    @Override
    public Query visitWhereClause(ElasticSearchParser.WhereClauseContext ctx) {
        return null;
    }

    @Override
    public Query visitGroupClause(ElasticSearchParser.GroupClauseContext ctx) {
        return null;
    }

    @Override
    public Query visitOrderClause(ElasticSearchParser.OrderClauseContext ctx) {
        return null;
    }

    @Override
    public Query visitOrder(ElasticSearchParser.OrderContext ctx) {
        return null;
    }

    @Override
    public Query visitLimitClause(ElasticSearchParser.LimitClauseContext ctx) {
        return null;
    }

    @Override
    public Query visitGranularityClause(ElasticSearchParser.GranularityClauseContext ctx) {
        return null;
    }

    @Override
    public Query visitSimpleGran(ElasticSearchParser.SimpleGranContext ctx) {
        return null;
    }

    @Override
    public Query visitDurationGran(ElasticSearchParser.DurationGranContext ctx) {
        return null;
    }

    @Override
    public Query visitPeriodGran(ElasticSearchParser.PeriodGranContext ctx) {
        return null;
    }

    @Override
    public Query visit(ParseTree tree) {
        return null;
    }

    @Override
    public Query visitChildren(RuleNode node) {
        return null;
    }

    @Override
    public Query visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public Query visitErrorNode(ErrorNode node) {
        return null;
    }
}

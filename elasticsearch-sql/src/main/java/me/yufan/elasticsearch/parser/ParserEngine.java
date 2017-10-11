package me.yufan.elasticsearch.parser;

import lombok.experimental.UtilityClass;
import me.yufan.elasticsearch.common.utils.StringUtils;
import me.yufan.elasticsearch.parser.exception.ElasticSearchSQLException;
import me.yufan.elasticsearch.parser.impl.ElasticSearchVisitor;
import me.yufan.elasticsearch.parser.impl.LexerErrorListener;
import me.yufan.elasticsearch.parser.impl.SyntaxErrorListener;
import me.yufan.elasticsearch.parser.impl.ThrowingExceptionErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

@UtilityClass
public class ParserEngine {

    public static SQLTemplate parse(String sql) {
        if (StringUtils.isBlank(sql)) {
            throw new ElasticSearchSQLException("blank sql is not allowed");
        }

        CharStream input = CharStreams.fromString(sql);
        ThrowingExceptionErrorStrategy errorHandler = new ThrowingExceptionErrorStrategy();

        ElasticSearchLexer lexer = new ElasticSearchLexer(input);
        lexer.removeErrorListeners();
        // Lexer error handler
        lexer.addErrorListener(new LexerErrorListener());

        CommonTokenStream token = new CommonTokenStream(lexer);

        ElasticSearchParser parser = new ElasticSearchParser(token);
        // Parser error handler
        parser.setErrorHandler(errorHandler);
        parser.removeErrorListeners();
        parser.addErrorListener(new SyntaxErrorListener());

        ElasticSearchParser.ProgContext progTree = parser.prog();
        ElasticSearchVisitor visitor = new ElasticSearchVisitor();

        SQLTemplate parsedResult = visitor.visit(progTree);
        if (parsedResult.isSuccess()) {
            return parsedResult;
        }
        throw new ElasticSearchSQLException("SQL parser error !!! " + parsedResult.getFailureReason());
    }
}

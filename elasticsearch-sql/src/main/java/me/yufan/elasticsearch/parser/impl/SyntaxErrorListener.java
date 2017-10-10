package me.yufan.elasticsearch.parser.impl;

import me.yufan.elasticsearch.parser.exception.SyntaxErrorException;
import me.yufan.elasticsearch.utils.AntlrUtils;
import org.antlr.v4.runtime.*;

public class SyntaxErrorListener extends BaseErrorListener {

    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {
        String position = "line " + line + ", pos " + charPositionInLine;
        String tokenName = "";
        String hint = "";

        if (offendingSymbol != null && offendingSymbol instanceof Token && recognizer != null && recognizer instanceof Parser) {
            Token token = (Token) offendingSymbol;
            tokenName = token.getText();
            String fullText = ((Parser) recognizer).getTokenStream().getTokenSource().getInputStream().toString();
            hint = AntlrUtils.underlineError(fullText, tokenName, line, charPositionInLine);
        }
        throw new SyntaxErrorException(position + " near " + tokenName + " : " + msg + "\n" + hint, e);
    }
}

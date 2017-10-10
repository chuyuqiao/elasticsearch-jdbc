package me.yufan.elasticsearch.parser.impl;

import me.yufan.elasticsearch.parser.exception.LexicalErrorException;
import me.yufan.elasticsearch.utils.AntlrUtils;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class LexerErrorListener extends BaseErrorListener {

    // offendingSymbol is null when lexer error
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {

        String position = "line " + line + ", pos " + charPositionInLine;
        String charText = "";
        String hint = "";
        if (recognizer != null && recognizer instanceof Lexer) {
            Lexer lexer = (Lexer) recognizer;
            String fullText = lexer.getInputStream().toString();
            charText = String.valueOf(fullText.charAt(lexer.getCharIndex()));
            hint = AntlrUtils.underlineError(fullText, charText, line, charPositionInLine);
        }
        throw new LexicalErrorException(position + " near " + charText + " : " + msg + "\n" + hint, e);
    }
}

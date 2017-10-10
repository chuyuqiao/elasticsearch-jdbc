package me.yufan.elasticsearch.parser.exception;

public class LexicalErrorException extends ParseException {
    private static final long serialVersionUID = -419231283145881004L;

    public LexicalErrorException(String msg) {
        super(msg);
    }

    public LexicalErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

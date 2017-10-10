package me.yufan.elasticsearch.parser.exception;

public class SyntaxErrorException extends ParseException {
    private static final long serialVersionUID = -6739848150530002953L;

    public SyntaxErrorException(String msg) {
        super(msg);
    }

    public SyntaxErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

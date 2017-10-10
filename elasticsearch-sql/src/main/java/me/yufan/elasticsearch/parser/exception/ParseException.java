package me.yufan.elasticsearch.parser.exception;


public class ParseException extends ElasticSearchSQLException {
    private static final long serialVersionUID = -9200100113032903935L;

    public ParseException(String msg) {
        super(msg);
    }

    public ParseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

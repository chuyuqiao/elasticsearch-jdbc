package me.yufan.elasticsearch.parser.exception;


public class ElasticSearchSQLException extends RuntimeException {
    private static final long serialVersionUID = -1287476555109119065L;

    public ElasticSearchSQLException(String msg) {
        super(msg);
    }

    public ElasticSearchSQLException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

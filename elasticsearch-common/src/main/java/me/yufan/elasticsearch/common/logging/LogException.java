package me.yufan.elasticsearch.common.logging;

public class LogException extends RuntimeException {
    private static final long serialVersionUID = 3784358252917101012L;

    public LogException() {
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }
}

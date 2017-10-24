package me.yufan.elasticsearch.common.json;

public class JSONException extends RuntimeException {
    private static final long serialVersionUID = 113951594291149908L;

    public JSONException() {
        super();
    }

    public JSONException(String message) {
        super(message);
    }

    public JSONException(String message, Throwable cause) {
        super(message, cause);
    }

    public JSONException(Throwable cause) {
        super(cause);
    }
}

package me.yufan.elasticsearch.parser;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ParserResult {

    /**
     * Parsing status, this would be true if no exception was thrown.
     */
    private boolean success;

    /**
     * The readable error message for parsing failure
     */
    private String failureReason;

    private SQLTemplate template;

    private ParserResult() {
        // No public constructor provided
    }

    public static ParserResult failed(String failureReason) {
        return new ParserResult().setSuccess(false).setFailureReason(failureReason);
    }

    public static ParserResult success() {
        return new ParserResult().setSuccess(true);
    }

    public static ParserResult success(SQLTemplate template) {
        return new ParserResult().setSuccess(true).setTemplate(template);
    }
}

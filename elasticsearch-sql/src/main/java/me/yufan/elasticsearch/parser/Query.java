package me.yufan.elasticsearch.parser;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO Add common function for parsing result
 */
@Data
public class Query implements Serializable {
    private static final long serialVersionUID = 8736092220159744170L;

    private boolean success;

    private String failureReason;
}

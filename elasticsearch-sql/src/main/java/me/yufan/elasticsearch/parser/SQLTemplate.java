package me.yufan.elasticsearch.parser;

import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.operands.LimitOperand;
import me.yufan.elasticsearch.model.Operand;
import me.yufan.elasticsearch.model.operands.OrderByOperand;

import java.io.Serializable;
import java.util.List;

/**
 * The parsing template for sql abstraction
 */
@Data
public class SQLTemplate implements Serializable {
    private static final long serialVersionUID = 8736092220159744170L;

    /**
     * Parsing status, this would be true if no exception was thrown.
     */
    private boolean success;

    /**
     * The readable error message for parsing failure
     */
    private String failureReason;

    /**
     * Table name
     */
    private String table;

    private List<Operand> columns;

    private BooleanExpr whereClause;

    private List<Operand> groupBys;

    private List<OrderByOperand> orderBy;

    private LimitOperand limit;
}

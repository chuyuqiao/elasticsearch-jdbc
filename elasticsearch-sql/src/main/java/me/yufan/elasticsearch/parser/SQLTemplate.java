package me.yufan.elasticsearch.parser;

import lombok.Data;
import lombok.experimental.Accessors;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.operands.LimitOperand;
import me.yufan.elasticsearch.model.Operand;
import me.yufan.elasticsearch.model.operands.OrderByOperand;

import java.io.Serializable;
import java.util.List;

/**
 * The middleware template for sql abstraction
 */
@Data
@Accessors(chain = true)
public class SQLTemplate implements Serializable {
    private static final long serialVersionUID = 8736092220159744170L;

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

package me.yufan.elasticsearch.model.operands.binary;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public abstract class BinaryOperand implements Operand {
    private static final long serialVersionUID = -2713200395462595815L;

    private final Operand left;

    private final Operand right;

    private final String operator;
}

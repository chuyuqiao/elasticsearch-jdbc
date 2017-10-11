package me.yufan.elasticsearch.model.operands.math;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class ConditionAggregationOperand implements Operand {
    private static final long serialVersionUID = 8481465828063779851L;

    private final Operand innerOperand;

    private final BooleanExpr condition;
}

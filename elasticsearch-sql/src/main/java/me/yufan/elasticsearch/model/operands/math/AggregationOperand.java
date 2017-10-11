package me.yufan.elasticsearch.model.operands.math;

import me.yufan.elasticsearch.model.Operand;
import me.yufan.elasticsearch.model.operands.column.NameOperand;

public interface AggregationOperand extends Operand {

    String getType();

    NameOperand getName();
}

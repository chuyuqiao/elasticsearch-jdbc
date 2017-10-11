package me.yufan.elasticsearch.model.operands.math;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.operands.column.NameOperand;

@Data
@AllArgsConstructor
public class MaxOperand implements AggregationOperand {
    private static final long serialVersionUID = 5219047832784030521L;

    private final String type;

    private final NameOperand name;
}

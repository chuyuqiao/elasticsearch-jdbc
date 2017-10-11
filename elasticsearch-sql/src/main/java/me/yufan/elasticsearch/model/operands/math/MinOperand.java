package me.yufan.elasticsearch.model.operands.math;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.operands.column.NameOperand;

@Data
@AllArgsConstructor
public class MinOperand implements AggregationOperand {
    private static final long serialVersionUID = 7094750494953569919L;

    private final String type;

    private final NameOperand name;
}

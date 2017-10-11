package me.yufan.elasticsearch.model.operands.math;

import lombok.Data;
import lombok.Getter;
import me.yufan.elasticsearch.model.operands.column.NameOperand;

@Data
@Getter
public class SumOperand implements AggregationOperand {
    private static final long serialVersionUID = -5676694595251670315L;

    private final String type;

    private final NameOperand name;
}

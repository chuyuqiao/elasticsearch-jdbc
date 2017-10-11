package me.yufan.elasticsearch.model.operands.column;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class DistinctOperand implements Operand {
    private static final long serialVersionUID = 399123049679085121L;

    private final Operand innerOperand;
}

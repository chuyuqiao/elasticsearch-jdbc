package me.yufan.elasticsearch.model.operands.column;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class CountOperand implements Operand {
    private static final long serialVersionUID = -210547749806396342L;

    private final String type;

    private final Operand name;
}

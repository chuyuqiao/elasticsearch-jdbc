package me.yufan.elasticsearch.model.operands.column;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class AliasOperand implements Operand {
    private static final long serialVersionUID = -7948628072985752493L;

    private final Operand operand;

    private final String alias;
}

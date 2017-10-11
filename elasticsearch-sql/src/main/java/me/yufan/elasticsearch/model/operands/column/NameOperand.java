package me.yufan.elasticsearch.model.operands.column;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class NameOperand implements Operand {
    private static final long serialVersionUID = 3688547048253629335L;

    private final String table;

    private final String column;
}

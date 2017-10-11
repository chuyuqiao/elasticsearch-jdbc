package me.yufan.elasticsearch.model.operands;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class OrderByOperand implements Operand {
    private static final long serialVersionUID = -1025346675523840093L;

    private final Operand operand;

    private final boolean desc;
}

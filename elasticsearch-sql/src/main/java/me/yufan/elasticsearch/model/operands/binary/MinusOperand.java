package me.yufan.elasticsearch.model.operands.binary;

import me.yufan.elasticsearch.model.Operand;

public class MinusOperand extends BinaryOperand {
    private static final long serialVersionUID = -3539089175669774851L;

    public MinusOperand(Operand left, Operand right) {
        super(left, right, "-");
    }
}

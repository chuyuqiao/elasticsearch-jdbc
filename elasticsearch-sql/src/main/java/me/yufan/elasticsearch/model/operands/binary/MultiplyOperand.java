package me.yufan.elasticsearch.model.operands.binary;

import me.yufan.elasticsearch.model.Operand;

public class MultiplyOperand extends BinaryOperand {
    private static final long serialVersionUID = 5247977580242655973L;

    public MultiplyOperand(Operand left, Operand right) {
        super(left, right, "*");
    }
}

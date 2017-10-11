package me.yufan.elasticsearch.model.operands.binary;

import me.yufan.elasticsearch.model.Operand;

public class DividOperand extends BinaryOperand {
    private static final long serialVersionUID = 1047850704234007956L;

    public DividOperand(Operand left, Operand right) {
        super(left, right, "/");
    }
}

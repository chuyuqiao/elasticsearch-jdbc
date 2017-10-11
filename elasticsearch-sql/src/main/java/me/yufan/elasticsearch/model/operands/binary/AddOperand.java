package me.yufan.elasticsearch.model.operands.binary;

import me.yufan.elasticsearch.model.Operand;

public class AddOperand extends BinaryOperand {
    private static final long serialVersionUID = 2060199000718667650L;

    public AddOperand(Operand left, Operand right) {
        super(left, right, "+");
    }
}

package me.yufan.elasticsearch.model.operands.binary;

import me.yufan.elasticsearch.model.Operand;

public class QuotientOperand extends BinaryOperand {
    private static final long serialVersionUID = -667952502091725202L;

    public QuotientOperand(Operand left, Operand right) {
        super(left, right, "quotient");
    }
}

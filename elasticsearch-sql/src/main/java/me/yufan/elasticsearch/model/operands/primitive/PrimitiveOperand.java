package me.yufan.elasticsearch.model.operands.primitive;

import me.yufan.elasticsearch.model.Operand;

public interface PrimitiveOperand extends Operand {

    String getValue();

    String getType();
}

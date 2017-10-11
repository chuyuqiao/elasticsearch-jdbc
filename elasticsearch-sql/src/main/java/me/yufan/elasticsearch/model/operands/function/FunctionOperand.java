package me.yufan.elasticsearch.model.operands.function;

import me.yufan.elasticsearch.model.Operand;
import me.yufan.elasticsearch.model.operands.enums.FunctionType;
import me.yufan.elasticsearch.model.operands.primitive.PrimitiveOperand;

import java.util.List;

public interface FunctionOperand extends Operand {

    FunctionType getType();

    List<PrimitiveOperand> getOperands();
}

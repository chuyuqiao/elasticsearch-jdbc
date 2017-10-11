package me.yufan.elasticsearch.model.operands.function;

import me.yufan.elasticsearch.common.logging.Logger;
import me.yufan.elasticsearch.common.logging.LoggerFactory;
import me.yufan.elasticsearch.model.operands.enums.FunctionType;
import me.yufan.elasticsearch.model.operands.primitive.PrimitiveOperand;

import java.util.List;

public class MaxOperand extends AbstractFunctionOperand {
    private static final long serialVersionUID = 5219047832784030521L;

    private static final Logger log = LoggerFactory.getLog(MaxOperand.class);

    public MaxOperand(List<PrimitiveOperand> operands) {
        super(operands);
        if (operands.size() > 1) {
            log.warn("Max operand only support on operand");
        }
    }

    @Override
    public FunctionType getType() {
        return FunctionType.MAX;
    }
}

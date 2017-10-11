package me.yufan.elasticsearch.model.operands.function;

import me.yufan.elasticsearch.common.logging.Logger;
import me.yufan.elasticsearch.common.logging.LoggerFactory;
import me.yufan.elasticsearch.model.operands.enums.FunctionType;
import me.yufan.elasticsearch.model.operands.primitive.PrimitiveOperand;

import java.util.List;

public class MinOperand extends AbstractFunctionOperand {
    private static final long serialVersionUID = 7094750494953569919L;

    private static final Logger log = LoggerFactory.getLog(MinOperand.class);

    public MinOperand(List<PrimitiveOperand> operands) {
        super(operands);
        if (operands.size() > 1) {
            log.warn("Min operand only support on operand");
        }
    }

    @Override
    public FunctionType getType() {
        return FunctionType.MIN;
    }
}

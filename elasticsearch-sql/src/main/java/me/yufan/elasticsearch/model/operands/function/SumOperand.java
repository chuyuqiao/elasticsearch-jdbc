package me.yufan.elasticsearch.model.operands.function;

import me.yufan.elasticsearch.common.logging.Logger;
import me.yufan.elasticsearch.common.logging.LoggerFactory;
import me.yufan.elasticsearch.model.operands.enums.FunctionType;
import me.yufan.elasticsearch.model.operands.primitive.PrimitiveOperand;

import java.util.List;

public class SumOperand extends AbstractFunctionOperand {
    private static final long serialVersionUID = -5676694595251670315L;

    private static final Logger log = LoggerFactory.getLog(SumOperand.class);

    public SumOperand(List<PrimitiveOperand> operands) {
        super(operands);

        if (operands.size() > 1) {
            log.warn("Sum operand only support on operand");
        }
    }

    @Override
    public FunctionType getType() {
        return FunctionType.SUM;
    }
}

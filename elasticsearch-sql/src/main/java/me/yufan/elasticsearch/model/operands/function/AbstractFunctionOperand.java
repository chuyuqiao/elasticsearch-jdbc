package me.yufan.elasticsearch.model.operands.function;

import lombok.NonNull;
import me.yufan.elasticsearch.model.operands.primitive.PrimitiveOperand;

import java.util.List;

public abstract class AbstractFunctionOperand implements FunctionOperand {
    private static final long serialVersionUID = 7565136778305339706L;

    private final List<PrimitiveOperand> operands;

    protected AbstractFunctionOperand(@NonNull List<PrimitiveOperand> operands) {
        this.operands = operands;
    }

    @Override
    public List<PrimitiveOperand> getOperands() {
        return operands;
    }
}

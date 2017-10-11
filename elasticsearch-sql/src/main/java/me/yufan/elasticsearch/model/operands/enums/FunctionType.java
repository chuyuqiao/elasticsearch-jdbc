package me.yufan.elasticsearch.model.operands.enums;

import lombok.Getter;
import me.yufan.elasticsearch.model.operands.function.FunctionOperand;
import me.yufan.elasticsearch.model.operands.function.MaxOperand;
import me.yufan.elasticsearch.model.operands.function.MinOperand;
import me.yufan.elasticsearch.model.operands.function.SumOperand;
import me.yufan.elasticsearch.model.operands.primitive.PrimitiveOperand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public enum FunctionType {

    MIN(MinOperand::new, "min"),
    SUM(SumOperand::new, "sum"),
    MAX(MaxOperand::new, "max");

    private static Map<String, FunctionType> functionMap = new HashMap<>();

    @Getter
    private List<String> functionId; // Support function alias

    private Function<List<PrimitiveOperand>, FunctionOperand> convert;

    FunctionType(Function<List<PrimitiveOperand>, FunctionOperand> convert, String... functionId) {
        this.convert = convert;
        this.functionId = Arrays.asList(functionId);
    }

    public FunctionOperand convert(List<PrimitiveOperand> operands) {
        return convert.apply(operands);
    }
}

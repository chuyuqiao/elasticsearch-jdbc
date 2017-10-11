package me.yufan.elasticsearch.model.operands.function;

import lombok.experimental.UtilityClass;
import me.yufan.elasticsearch.model.operands.enums.FunctionType;
import me.yufan.elasticsearch.model.operands.primitive.PrimitiveOperand;
import me.yufan.elasticsearch.parser.exception.ParseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class FunctionFactory {

    private final Map<String, FunctionType> functionMap = new HashMap<>();

    public FunctionOperand newInstance(String functionId, List<PrimitiveOperand> operands) {
        if (functionMap.isEmpty()) {
            initFunctionMap();
        }
        FunctionType type = functionMap.get(functionId);
        if (type == null) {
            throw new ParseException("No such function " + functionId + " supported");
        }
        return type.convert(operands);
    }

    // TODO drop this shit method
    private synchronized void initFunctionMap() {
        if (functionMap.isEmpty() && FunctionType.values().length > 0) {
            for (FunctionType type : FunctionType.values()) {
                for (String id : type.getFunctionId()) {
                    functionMap.put(id, type);
                }
            }
        }
    }
}

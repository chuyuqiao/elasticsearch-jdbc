package me.yufan.elasticsearch.model.operands.primitive;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FloatPrimitiveOperand implements PrimitiveOperand {
    private final String value;

    @Override
    public String getType() {
        return "double";
    }
}

package me.yufan.elasticsearch.model.operands.primitive;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FloatPrimitiveOperand implements PrimitiveOperand {
    private static final long serialVersionUID = -2657213394553372158L;

    private final String value;

    @Override
    public String getType() {
        return "double";
    }
}

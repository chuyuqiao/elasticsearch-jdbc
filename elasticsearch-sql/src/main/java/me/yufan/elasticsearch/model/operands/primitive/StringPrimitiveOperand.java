package me.yufan.elasticsearch.model.operands.primitive;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StringPrimitiveOperand implements PrimitiveOperand {
    private static final long serialVersionUID = -1495806302722273771L;

    private final String value;

    @Override
    public String getType() {
        return "string";
    }
}

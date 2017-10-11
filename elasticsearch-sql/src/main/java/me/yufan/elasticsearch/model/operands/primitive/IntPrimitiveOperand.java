package me.yufan.elasticsearch.model.operands.primitive;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class IntPrimitiveOperand implements PrimitiveOperand {
    private static final long serialVersionUID = 4811235592827844483L;

    private final String value;

    @Override
    public String getType() {
        return "long";
    }
}

package me.yufan.elasticsearch.model.operands.primitive;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NameOperand implements PrimitiveOperand {
    private static final long serialVersionUID = 3688547048253629335L;

    private final String table;

    private final String column;

    @Override
    public String getValue() {
        return column; // Table name is useless?
    }

    @Override
    public String getType() {
        return null;
    }
}

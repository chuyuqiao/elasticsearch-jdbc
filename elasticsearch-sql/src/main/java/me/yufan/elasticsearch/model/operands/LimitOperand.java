package me.yufan.elasticsearch.model.operands;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class LimitOperand implements Operand {
    private static final long serialVersionUID = -7434992210077924402L;

    private final int offset;
    private final int resultCount;

    public int getMaxSize() {
        return offset + resultCount;
    }
}

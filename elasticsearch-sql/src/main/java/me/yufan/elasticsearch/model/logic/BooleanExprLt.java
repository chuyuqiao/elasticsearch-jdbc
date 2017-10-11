package me.yufan.elasticsearch.model.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class BooleanExprLt implements BooleanExpr {
    private static final long serialVersionUID = -882223982319911133L;

    private final Operand left;

    private final Operand right;

    /**
     * Combine le and lt
     */
    private final Boolean isEquals;
}

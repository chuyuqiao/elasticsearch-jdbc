package me.yufan.elasticsearch.model.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class BooleanExprGt implements BooleanExpr {
    private static final long serialVersionUID = 7157990676388983594L;

    private final Operand left;

    private final Operand right;

    /**
     * Combine ge and gt
     */
    private final Boolean isEquals;
}

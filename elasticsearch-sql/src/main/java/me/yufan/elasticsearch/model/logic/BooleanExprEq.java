package me.yufan.elasticsearch.model.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class BooleanExprEq implements BooleanExpr {
    private static final long serialVersionUID = -7536943346121132308L;

    private final Operand left;

    private final Operand right;
}

package me.yufan.elasticsearch.model.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;
import me.yufan.elasticsearch.model.Operand;

@Data
@AllArgsConstructor
public class BooleanExprBetween implements BooleanExpr {
    private static final long serialVersionUID = -8919459048693160668L;

    private final Operand left;

    private final Operand right;
}

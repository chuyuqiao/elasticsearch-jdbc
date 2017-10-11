package me.yufan.elasticsearch.model.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;

@Data
@AllArgsConstructor
public class BooleanExprBetween implements BooleanExpr {
    private static final long serialVersionUID = -8919459048693160668L;

    private final BooleanExpr left;

    private final BooleanExpr right;
}

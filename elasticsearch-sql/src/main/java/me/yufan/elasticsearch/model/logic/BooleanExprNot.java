package me.yufan.elasticsearch.model.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;

@Data
@AllArgsConstructor
public class BooleanExprNot implements BooleanExpr {
    private static final long serialVersionUID = 7032585381498017187L;

    private final BooleanExpr inner;
}

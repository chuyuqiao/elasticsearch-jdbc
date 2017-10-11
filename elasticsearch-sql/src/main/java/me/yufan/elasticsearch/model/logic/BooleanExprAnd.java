package me.yufan.elasticsearch.model.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;

@Data
@AllArgsConstructor
public class BooleanExprAnd implements BooleanExpr {
    private static final long serialVersionUID = -1375044074288669796L;

    private final BooleanExpr left;

    private final BooleanExpr right;
}

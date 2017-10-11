package me.yufan.elasticsearch.model.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.model.BooleanExpr;

import java.util.List;

@Data
@AllArgsConstructor
public class BooleanExprOr implements BooleanExpr {
    private static final long serialVersionUID = -4285574185912205033L;

    private final List<BooleanExpr> innerExpr;
}

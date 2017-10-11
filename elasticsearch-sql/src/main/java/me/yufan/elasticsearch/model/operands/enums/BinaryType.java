package me.yufan.elasticsearch.model.operands.enums;

import lombok.AllArgsConstructor;
import me.yufan.elasticsearch.model.Operand;
import me.yufan.elasticsearch.model.operands.binary.*;
import me.yufan.elasticsearch.parser.ElasticSearchLexer;
import me.yufan.elasticsearch.parser.exception.ParseException;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@AllArgsConstructor
public enum BinaryType {

    ADD(ElasticSearchLexer.PLUS, AddOperand::new),
    MINUS(ElasticSearchLexer.MINUS, MinusOperand::new),
    MULTIPLY(ElasticSearchLexer.STAR, MultiplyOperand::new),
    DIVIDE(ElasticSearchLexer.SLASH, DivideOperand::new),
    QUOTIENT(ElasticSearchLexer.MOD, QuotientOperand::new);

    private static Map<Integer, BinaryType> tokenMap = new HashMap<>();

    private int lexerIndex;

    private BiFunction<Operand, Operand, BinaryOperand> convert;

    public static BinaryOperand newInstance(Token typeToken, Operand left, Operand right) {
        // Lazy init map would be a good choice, any solution ?
        BinaryType type = tokenMap.computeIfAbsent(typeToken.getType(), integer -> {
            for (BinaryType token : BinaryType.values()) {
                if (integer.equals(token.lexerIndex)) {
                    return token;
                }
            }
            return null;
        });
        if (type == null) {
            throw new ParseException("This token " + typeToken.getText() + " is not supported");
        }
        return type.convert.apply(left, right);
    }
}

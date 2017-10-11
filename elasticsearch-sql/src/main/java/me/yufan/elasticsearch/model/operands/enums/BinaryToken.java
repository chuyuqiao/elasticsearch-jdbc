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
public enum BinaryToken {

    ADD(ElasticSearchLexer.PLUS, AddOperand::new),
    MINUS(ElasticSearchLexer.MINUS, MinusOperand::new),
    MULTIPLY(ElasticSearchLexer.STAR, MultiplyOperand::new),
    DIVIDE(ElasticSearchLexer.SLASH, DivideOperand::new),
    QUOTIENT(ElasticSearchLexer.MOD, QuotientOperand::new);

    private static Map<Integer, BinaryToken> tokenMap = new HashMap<>();

    private int lexerIndex;

    private BiFunction<Operand, Operand, Operand> convert;

    public static Operand newInstance(Token typeToken, Operand left, Operand right) {
        // Lazy init map would be a good choice, any solution ?
        BinaryToken binaryToken = tokenMap.computeIfAbsent(typeToken.getType(), integer -> {
            for (BinaryToken token : BinaryToken.values()) {
                if (integer.equals(token.lexerIndex)) {
                    return token;
                }
            }
            return null;
        });
        if (binaryToken == null) {
            throw new ParseException("This token " + typeToken.getText() + " is not supported");
        }
        return binaryToken.convert.apply(left, right);
    }
}

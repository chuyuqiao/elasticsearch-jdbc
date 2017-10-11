package me.yufan.elasticsearch.model.operands.primitive;

import lombok.NonNull;

public class StringPrimitiveOperand implements PrimitiveOperand {
    private static final long serialVersionUID = -1495806302722273771L;

    private String value;

    public StringPrimitiveOperand(@NonNull String value) {
        this.value = value;
        removeQuote();
    }

    private void removeQuote() {
        int startIndex = 0;
        int endIndex = value.length();
        if (isQuote(startIndex)) {
            startIndex = 1;
        }
        if (isQuote(endIndex - 1)) {
            endIndex = endIndex - 1;
        }
        value = value.substring(startIndex, endIndex);
    }

    private boolean isQuote(int index) {
        return value.charAt(index) == '\'' || value.charAt(index) == '\"' || value.charAt(index) == '`';
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "string";
    }
}

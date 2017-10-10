package me.yufan.elasticsearch.utils;

import lombok.experimental.UtilityClass;

/**
 * Util class for generate a beautiful error report
 */
@UtilityClass
public class AntlrUtils {

    /**
     * @param line start from 1
     */
    public String underlineError(String fullText, String symbolText, int line, int charPositionInLine) {
        StringBuilder buffer = new StringBuilder("\n\n");

        buffer.append(errorLine(fullText, line));
        buffer.append("\n");

        for (int i = 0; i < charPositionInLine; i++) {
            buffer.append(" ");
        }

        for (int i = 0; i < symbolText.length(); i++) {
            buffer.append("^");
        }

        buffer.append("\n");
        return buffer.toString();
    }

    String errorLine(String errorMessage, int lineNumber) {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < lineNumber; i++) {
            int index = errorMessage.indexOf('\n', endIndex + 1);
            if (i > 0) {
                startIndex = endIndex + 1;
            }
            if (index >= 0) {
                endIndex = index;
            } else {
                endIndex = errorMessage.length();
            }
        }
        return errorMessage.substring(startIndex, endIndex);
    }
}

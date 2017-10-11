package me.yufan.elasticsearch.model.operands.primitive;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringPrimitiveOperandTest {

    @Test
    public void operand_should_auto_unwrap_quote() {
        StringPrimitiveOperand operand = new StringPrimitiveOperand("\"dsdsdsdsds");
        assertThat(operand.getValue(), equalTo("dsdsdsdsds"));

        StringPrimitiveOperand operand1 = new StringPrimitiveOperand("\"dsdsdsdsds\"");
        assertThat(operand1.getValue(), equalTo("dsdsdsdsds"));
    }
}

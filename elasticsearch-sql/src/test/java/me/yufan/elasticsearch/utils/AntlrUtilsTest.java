package me.yufan.elasticsearch.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AntlrUtilsTest {

    @Test
    public void get_specified_line_in_multiply_line_str() throws Exception {
        String text = "aaa\nbbb\nccc\nddd";

        assertThat(AntlrUtils.errorLine(text, 1), is("aaa"));
        assertThat(AntlrUtils.errorLine(text, 2), is("bbb"));
        assertThat(AntlrUtils.errorLine(text, 3), is("ccc"));
        assertThat(AntlrUtils.errorLine(text, 4), is("ddd"));
    }
}

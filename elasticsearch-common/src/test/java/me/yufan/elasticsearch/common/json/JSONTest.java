package me.yufan.elasticsearch.common.json;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;

public class JSONTest {

    @Test
    public void jackson_should_be_used_when_directly_call() {
        JSONFactory.useJackson();

        List<String> list = Collections.nCopies(8, "aaaaaa");
        String jsonString = JSON.toJSONString(list);
        assertThat(jsonString, CoreMatchers.containsString("[\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\"]"));
    }

    @Test
    public void gson_should_be_used_when_directly_call() {
        JSONFactory.useGson();

        List<String> list = Collections.nCopies(8, "aaaaaa");
        String jsonString = JSON.toJSONString(list);
        assertThat(jsonString, CoreMatchers.containsString("[\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\",\"aaaaaa\"]"));
    }
}

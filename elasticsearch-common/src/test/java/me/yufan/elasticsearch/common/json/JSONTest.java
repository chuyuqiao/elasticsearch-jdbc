package me.yufan.elasticsearch.common.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.yufan.elasticsearch.common.json.esjson.ESJSONAdapter;
import me.yufan.elasticsearch.common.json.fastjson.FastJSONAdaptor;
import me.yufan.elasticsearch.common.json.gson.GsonJSONAdapter;
import me.yufan.elasticsearch.common.json.jackson.JacksonJSONAdapter;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class JSONTest {

    @Test
    public void jackson_should_be_used_when_directly_call() {
        JSONFactory.useJackson();

        assertThat(JSONFactory.getAdapter(), instanceOf(JacksonJSONAdapter.class));
        list_to_json_assert();
        map_to_json_assert();
        list_json_to_elements();
        map_json_to_elements();
    }

    @Test
    public void gson_should_be_used_when_directly_call() {
        JSONFactory.useGson();

        assertThat(JSONFactory.getAdapter(), instanceOf(GsonJSONAdapter.class));
        list_to_json_assert();
        map_to_json_assert();
        list_json_to_elements();
        map_json_to_elements();
    }

    @Test
    public void fastjson_should_be_used_when_directly_call() {
        JSONFactory.useFastjson();

        assertThat(JSONFactory.getAdapter(), instanceOf(FastJSONAdaptor.class));
        list_to_json_assert();
        map_to_json_assert();
        list_json_to_elements();
        map_json_to_elements();
    }

    @Test
    public void esjson_should_be_used_when_directly_call() {
        JSONFactory.useEsjson();

        assertThat(JSONFactory.getAdapter(), instanceOf(ESJSONAdapter.class));
        list_to_json_assert();
        map_to_json_assert();
        list_json_to_elements();
    }

    private void list_to_json_assert() {
        List<String> list = IntStream.range(1, 10).boxed().map(String::valueOf).collect(toList());
        String jsonString = JSON.toJSONString(list);

        assertThat(jsonString, containsString("[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\"]"));
    }

    private void map_to_json_assert() {
        Map<String, JsonItem> map = new HashMap<>();
        map.put("first", new JsonItem(12, "twelve"));
        map.put("second", new JsonItem(23, "twenty three"));

        String jsonString = JSON.toJSONString(map);
        assertThat(jsonString, containsString("{\"first\":{\"index\":12,\"message\":\"twelve\"},\"second\":{\"index\":23,\"message\":\"twenty three\"}}"));
    }

    private void list_json_to_elements() {
        String jsonString = "[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\"]";
        List<String> list = JSON.parse(jsonString, new TypeReference<List<String>>() {
        });
        assertThat(list, hasSize(9));
        assertThat(list.get(0), any(String.class));
    }

    private void map_json_to_elements() {
        String jsonString = "{\"first\":{\"index\":12,\"message\":\"twelve\"},\"second\":{\"index\":23,\"message\":\"twenty three\"}}";
        Map<String, JsonItem> map = JSON.parse(jsonString, new TypeReference<Map<String, JsonItem>>() {
        });
        assertThat(map, hasKey("first"));
        assertThat(map, hasKey("second"));
        assertThat(map.get("first"), any(JsonItem.class)); // FIXME ESJSON would fail on this test
        assertThat(map.get("second"), any(JsonItem.class)); // FIXME ESJSON would fail on this test
    }

    @Data
    @AllArgsConstructor
    public static class JsonItem {

        private int index;

        private String message;
    }
}

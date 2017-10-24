package me.yufan.elasticsearch.common.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface JSONAdapter {

    String getName();

    <T> T parse(String json, Type type);

    String toJSONString(Object obj);

    JSONObject toJSONObject(Object obj);

    JSONArray toJSONArray(Object obj);

    JSONArray parseArray(String json);

    JSONObject parseObject(String json);

    JSONObject newJSONObject();

    JSONObject newJSONObject(Map<String, Object> map);

    JSONObject newJSONObject(int initialCapacity);

    JSONArray newJSONArray();

    JSONArray newJSONArray(List<Object> list);

    JSONArray newJSONArray(int initialCapacity);
}

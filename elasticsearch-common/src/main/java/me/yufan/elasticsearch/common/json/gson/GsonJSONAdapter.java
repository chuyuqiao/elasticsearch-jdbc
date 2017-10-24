package me.yufan.elasticsearch.common.json.gson;

import me.yufan.elasticsearch.common.json.JSONAdapter;
import me.yufan.elasticsearch.common.json.JSONArray;
import me.yufan.elasticsearch.common.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GsonJSONAdapter implements JSONAdapter {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public <T> T parse(String json, Type type) {
        return null;
    }

    @Override
    public String toJSONString(Object obj) {
        return null;
    }

    @Override
    public JSONObject toJSONObject(Object obj) {
        return null;
    }

    @Override
    public JSONArray toJSONArray(Object obj) {
        return null;
    }

    @Override
    public JSONArray parseArray(String json) {
        return null;
    }

    @Override
    public JSONObject parseObject(String json) {
        return null;
    }

    @Override
    public JSONObject newJSONObject() {
        return null;
    }

    @Override
    public JSONObject newJSONObject(Map<String, Object> map) {
        return null;
    }

    @Override
    public JSONObject newJSONObject(int initialCapacity) {
        return null;
    }

    @Override
    public JSONArray newJSONArray() {
        return null;
    }

    @Override
    public JSONArray newJSONArray(List<Object> list) {
        return null;
    }

    @Override
    public JSONArray newJSONArray(int initialCapacity) {
        return null;
    }
}

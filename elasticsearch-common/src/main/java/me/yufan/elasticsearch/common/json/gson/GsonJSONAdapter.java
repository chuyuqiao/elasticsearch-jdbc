package me.yufan.elasticsearch.common.json.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.yufan.elasticsearch.common.json.JSONAdapter;
import me.yufan.elasticsearch.common.json.JSONArray;
import me.yufan.elasticsearch.common.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GsonJSONAdapter implements JSONAdapter {

    private static final Gson gson = new Gson();

    @Override
    public String getName() {
        return "gson";
    }

    @Override
    public <T> T parse(String json, Type type) {
        return gson.fromJson(json, type);
    }

    @Override
    public String toJSONString(Object obj) {
        return gson.toJson(obj);
    }

    @Override
    public JSONObject toJSONObject(Object obj) {
        String json = gson.toJson(obj);
        return new GsonJSONObject(gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType()));
    }

    @Override
    public JSONArray toJSONArray(Object obj) {
        String json = gson.toJson(obj);
        return new GsonJSONArray(gson.fromJson(json, new TypeToken<List<Object>>() {
        }.getType()));
    }

    @Override
    public JSONArray parseArray(String json) {
        return new GsonJSONArray(gson.fromJson(json, new TypeToken<List<Object>>() {
        }.getType()));
    }

    @Override
    public JSONObject parseObject(String json) {
        return new GsonJSONObject(gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType()));
    }

    @Override
    public JSONObject newJSONObject() {
        return new GsonJSONObject();
    }

    @Override
    public JSONObject newJSONObject(Map<String, Object> map) {
        return new GsonJSONObject(map);
    }

    @Override
    public JSONObject newJSONObject(int initialCapacity) {
        return new GsonJSONObject(initialCapacity);
    }

    @Override
    public JSONArray newJSONArray() {
        return new GsonJSONArray();
    }

    @Override
    public JSONArray newJSONArray(List<Object> list) {
        return new GsonJSONArray(list);
    }

    @Override
    public JSONArray newJSONArray(int initialCapacity) {
        return new GsonJSONArray(initialCapacity);
    }
}

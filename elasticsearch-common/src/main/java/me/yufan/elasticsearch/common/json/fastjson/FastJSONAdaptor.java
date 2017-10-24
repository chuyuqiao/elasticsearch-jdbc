package me.yufan.elasticsearch.common.json.fastjson;

import com.alibaba.fastjson.JSON;
import me.yufan.elasticsearch.common.json.JSONAdapter;
import me.yufan.elasticsearch.common.json.JSONArray;
import me.yufan.elasticsearch.common.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class FastJSONAdaptor implements JSONAdapter {

    @Override
    public String getName() {
        return "fastjson";
    }

    @Override
    public <T> T parse(String json, Type type) {
        return JSON.parseObject(json, type);
    }

    @Override
    public String toJSONString(Object obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public JSONObject toJSONObject(Object obj) {
        return new FastJSONObject((com.alibaba.fastjson.JSONObject) JSON.toJSON(obj));
    }

    @Override
    public JSONArray toJSONArray(Object obj) {
        return new FastJSONArray((com.alibaba.fastjson.JSONArray) JSON.toJSON(obj));
    }

    @Override
    public JSONArray parseArray(String json) {
        return new FastJSONArray(JSON.parseArray(json));
    }

    @Override
    public JSONObject parseObject(String json) {
        return new FastJSONObject(JSON.parseObject(json));
    }

    @Override
    public JSONObject newJSONObject() {
        return new FastJSONObject(new com.alibaba.fastjson.JSONObject());
    }

    @Override
    public JSONObject newJSONObject(Map<String, Object> map) {
        return new FastJSONObject(new com.alibaba.fastjson.JSONObject(map));
    }

    @Override
    public JSONObject newJSONObject(int initialCapacity) {
        return new FastJSONObject(new com.alibaba.fastjson.JSONObject(initialCapacity));
    }

    @Override
    public JSONArray newJSONArray() {
        return new FastJSONArray(new com.alibaba.fastjson.JSONArray());
    }

    @Override
    public JSONArray newJSONArray(List<Object> list) {
        return new FastJSONArray(new com.alibaba.fastjson.JSONArray(list));
    }

    @Override
    public JSONArray newJSONArray(int initialCapacity) {
        return new FastJSONArray(new com.alibaba.fastjson.JSONArray(initialCapacity));
    }
}

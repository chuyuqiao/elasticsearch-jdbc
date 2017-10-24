package me.yufan.elasticsearch.common.json.esjson;

import me.yufan.elasticsearch.common.json.JSONAdapter;
import me.yufan.elasticsearch.common.json.JSONArray;
import me.yufan.elasticsearch.common.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ESJSONAdapter implements JSONAdapter {

    @Override
    public String getName() {
        return "esjson";
    }

    @Override
    public <T> T parse(String json, Type type) {
        return me.yufan.elasticsearch.common.json.esjson.core.JSONObject.parseObject(json, type);
    }

    @Override
    public String toJSONString(Object obj) {
        return me.yufan.elasticsearch.common.json.esjson.core.JSONObject.toJSONString(obj);
    }

    @Override
    public JSONObject toJSONObject(Object obj) {
        return new ESJSONObject(new me.yufan.elasticsearch.common.json.esjson.core.JSONObject(obj));
    }

    @Override
    public JSONArray toJSONArray(Object obj) {
        return new ESJSONArray(new me.yufan.elasticsearch.common.json.esjson.core.JSONArray(obj));
    }

    @Override
    public JSONArray parseArray(String json) {
        return new ESJSONArray(new me.yufan.elasticsearch.common.json.esjson.core.JSONArray(json));
    }

    @Override
    public JSONObject parseObject(String json) {
        return new ESJSONObject(new me.yufan.elasticsearch.common.json.esjson.core.JSONObject(json));
    }

    @Override
    public JSONObject newJSONObject() {
        return new ESJSONObject(new me.yufan.elasticsearch.common.json.esjson.core.JSONObject());
    }

    @Override
    public JSONObject newJSONObject(Map<String, Object> map) {
        return new ESJSONObject(new me.yufan.elasticsearch.common.json.esjson.core.JSONObject(map));
    }

    @Override
    public JSONObject newJSONObject(int initialCapacity) {
        return new ESJSONObject(new me.yufan.elasticsearch.common.json.esjson.core.JSONObject(initialCapacity));
    }

    @Override
    public JSONArray newJSONArray() {
        return new ESJSONArray(new me.yufan.elasticsearch.common.json.esjson.core.JSONArray());
    }

    @Override
    public JSONArray newJSONArray(List<Object> list) {
        return new ESJSONArray(new me.yufan.elasticsearch.common.json.esjson.core.JSONArray(list));
    }

    @Override
    public JSONArray newJSONArray(int initialCapacity) {
        return new ESJSONArray(new me.yufan.elasticsearch.common.json.esjson.core.JSONArray(initialCapacity));
    }
}

package me.yufan.elasticsearch.common.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.yufan.elasticsearch.common.json.JSONAdapter;
import me.yufan.elasticsearch.common.json.JSONArray;
import me.yufan.elasticsearch.common.json.JSONException;
import me.yufan.elasticsearch.common.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JacksonJSONAdapter implements JSONAdapter {

    private ObjectMapper mapper;

    public JacksonJSONAdapter() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public String getName() {
        return "jackson";
    }

    @Override
    public <T> T parse(String json, Type type) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructType(type));
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    @Override
    public String toJSONString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JSONException(e);
        }
    }

    @Override
    public JSONObject toJSONObject(Object obj) {
        Map<String, Object> map = mapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
        });
        return new JacksonJSONObject(map);
    }

    @Override
    public JSONArray toJSONArray(Object obj) {
        List<Object> list = mapper.convertValue(obj, new TypeReference<List<Object>>() {
        });
        return new JacksonJSONArray(list);
    }

    @Override
    public JSONArray parseArray(String json) {
        try {
            List<Object> value = mapper.readValue(json, new TypeReference<List<Object>>() {
            });
            return new JacksonJSONArray(value);
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    @Override
    public JSONObject parseObject(String json) {
        try {
            Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
            return new JacksonJSONObject(map);
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    @Override
    public JSONObject newJSONObject() {
        return new JacksonJSONObject();
    }

    @Override
    public JSONObject newJSONObject(Map<String, Object> map) {
        return new JacksonJSONObject(map);
    }

    @Override
    public JSONObject newJSONObject(int initialCapacity) {
        return new JacksonJSONObject(initialCapacity);
    }

    @Override
    public JSONArray newJSONArray() {
        return new JacksonJSONArray();
    }

    @Override
    public JSONArray newJSONArray(List<Object> list) {
        return new JacksonJSONArray(list);
    }

    @Override
    public JSONArray newJSONArray(int initialCapacity) {
        return new JacksonJSONArray(initialCapacity);
    }
}

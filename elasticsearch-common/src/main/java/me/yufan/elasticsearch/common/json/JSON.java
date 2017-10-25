package me.yufan.elasticsearch.common.json;

import lombok.experimental.UtilityClass;
import me.yufan.elasticsearch.common.utils.StringUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@UtilityClass
public class JSON {

    private JSONAdapter adapter = JSONFactory.getAdapter();

    public <T> T parse(String json, Type type) {
        try {
            if (StringUtils.isEmpty(json)) {
                return null;
            }
            return adapter.parse(json, type);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public <T> T parse(String json, TypeReference<T> typeReference) {
        try {
            if(StringUtils.isEmpty(json)){
                return null;
            }
            return adapter.parse(json, typeReference.getType());
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public String toJSONString(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            return adapter.toJSONString(obj);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONObject toJSONObject(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            return adapter.toJSONObject(obj);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONArray toJSONArray(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            return adapter.toJSONArray(obj);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONArray parseArray(String obj) {
        try {
            return adapter.parseArray(obj);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONObject parseObject(String obj) {
        try {
            return adapter.parseObject(obj);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONObject newJSONObject() {
        try {
            return adapter.newJSONObject();
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONArray newJSONArray() {
        try {
            return adapter.newJSONArray();
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONObject newJSONObject(Map<String, Object> map) {
        try {
            return adapter.newJSONObject(map);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONObject newJSONObject(int initialCapacity) {
        try {
            return adapter.newJSONObject(initialCapacity);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONArray newJSONArray(List<Object> list) {
        try {
            return adapter.newJSONArray(list);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    public JSONArray newJSONArray(int initialCapacity) {
        try {
            return adapter.newJSONArray(initialCapacity);
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    void setAdapter(JSONAdapter adapter) {
        JSON.adapter = adapter;
    }
}

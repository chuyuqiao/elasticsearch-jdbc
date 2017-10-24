package me.yufan.elasticsearch.common.json.jackson;

import me.yufan.elasticsearch.common.json.JSON;
import me.yufan.elasticsearch.common.json.JSONArray;
import me.yufan.elasticsearch.common.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import static me.yufan.elasticsearch.common.utils.TypeUtils.*;

public class JacksonJSONObject implements JSONObject {

    private final HashMap<String, Object> map;

    public JacksonJSONObject() {
        this.map = new HashMap<>(16);
    }

    public JacksonJSONObject(int initialCapacity) {
        this.map = new HashMap<>(initialCapacity);
    }

    public JacksonJSONObject(Map<String, Object> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public JSONObject getJSONObject(String key) {
        Object value = map.get(key);
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        } else {
            return JSON.toJSONObject(value);
        }
    }

    @Override
    public JSONArray getJSONArray(String key) {
        Object value = map.get(key);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        } else {
            return JSON.toJSONArray(value);
        }
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        Object value = map.get(key);
        return castToJavaBean(value, clazz);
    }

    @Override
    public Boolean getBoolean(String key) {
        Object value = map.get(key);
        return castToBoolean(value);
    }

    @Override
    public byte[] getBytes(String key) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        } else {
            return castToBytes(value);
        }
    }

    @Override
    public boolean getBooleanValue(String key) {
        Object value = map.get(key);
        if (value == null) {
            return false;
        } else {
            return castToBoolean(value);
        }
    }

    @Override
    public Byte getByte(String key) {
        Object value = map.get(key);
        return castToByte(value);
    }

    @Override
    public byte getByteValue(String key) {
        Object value = map.get(key);
        if (value == null) {
            return 0;
        } else {
            return castToByte(value);
        }
    }

    @Override
    public Short getShort(String key) {
        Object value = map.get(key);
        return castToShort(value);
    }

    @Override
    public short getShortValue(String key) {
        Object value = map.get(key);
        if (value == null) {
            return 0;
        } else {
            return castToShort(value);
        }
    }

    @Override
    public Integer getInteger(String key) {
        Object value = map.get(key);
        return castToInt(value);
    }

    @Override
    public int getIntValue(String key) {
        Object value = map.get(key);
        if (value == null) {
            return 0;
        } else {
            return castToInt(value);
        }
    }

    @Override
    public Long getLong(String key) {
        Object value = map.get(key);
        return castToLong(value);
    }

    @Override
    public long getLongValue(String key) {
        Object value = map.get(key);
        if (value == null) {
            return 0;
        } else {
            return castToLong(value);
        }
    }

    @Override
    public Float getFloat(String key) {
        Object value = map.get(key);
        return castToFloat(value);
    }

    @Override
    public float getFloatValue(String key) {
        Object value = map.get(key);
        if (value == null) {
            return 0.0F;
        } else {
            return castToFloat(value);
        }
    }

    @Override
    public Double getDouble(String key) {
        Object value = map.get(key);
        return castToDouble(value);
    }

    @Override
    public double getDoubleValue(String key) {
        Object value = map.get(key);
        if (value == null) {
            return 0.0D;
        } else {
            return castToDouble(value);
        }
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        Object value = map.get(key);
        return castToBigDecimal(value);
    }

    @Override
    public BigInteger getBigInteger(String key) {
        Object value = map.get(key);
        return castToBigInteger(value);
    }

    @Override
    public String getString(String key) {
        Object value = map.get(key);
        return castToString(value);
    }

    @Override
    public Date getDate(String key) {
        Object value = map.get(key);
        return castToDate(value);
    }

    @Override
    public java.sql.Date getSqlDate(String key) {
        Object value = map.get(key);
        return castToSqlDate(value);
    }

    @Override
    public Timestamp getTimestamp(String key) {
        Object value = map.get(key);
        return castToTimestamp(value);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object remove(String key) {
        return map.remove(key);
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Map.Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public String toJSONString() {
        return JSON.toJSONString(map);
    }

    @Override
    public String toString() {
        return toJSONString();
    }
}

package me.yufan.elasticsearch.common.json.gson;

import me.yufan.elasticsearch.common.json.JSON;
import me.yufan.elasticsearch.common.json.JSONArray;
import me.yufan.elasticsearch.common.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import static me.yufan.elasticsearch.common.utils.TypeUtils.*;
import static me.yufan.elasticsearch.common.utils.TypeUtils.castToTimestamp;

public class GsonJSONArray implements JSONArray {

    private final List<Object> list;

    public GsonJSONArray() {
        this(16);
    }

    public GsonJSONArray(int initialCapacity) {
        this.list = new ArrayList<>(initialCapacity);
    }

    public GsonJSONArray(List<Object> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<Object> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(Object e) {
        return list.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<?> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<?> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Object set(int index, Object element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, Object element) {
        list.add(index, element);
    }

    @Override
    public Object remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public Object get(int index) {
        return list.get(index);
    }

    @Override
    public JSONObject getJSONObject(int index) {
        Object item = list.get(index);
        return JSON.toJSONObject(item);
    }

    @Override
    public JSONArray getJSONArray(int index) {
        Object item = list.get(index);
        return JSON.toJSONArray(item);
    }

    @Override
    public <T> T getObject(int index, Class<T> clazz) {
        Object item = list.get(index);
        return castToJavaBean(item, clazz);
    }

    @Override
    public Boolean getBoolean(int index) {
        Object item = list.get(index);
        return castToBoolean(item);
    }

    @Override
    public boolean getBooleanValue(int index) {
        Object item = list.get(index);
        if (item == null) {
            return false;
        } else {
            return castToBoolean(item);
        }
    }

    @Override
    public Byte getByte(int index) {
        Object item = list.get(index);
        return castToByte(item);
    }

    @Override
    public byte getByteValue(int index) {
        Object item = list.get(index);
        if (item == null) {
            return 0;
        } else {
            return castToByte(item);
        }
    }

    @Override
    public Short getShort(int index) {
        Object item = list.get(index);
        return castToShort(item);
    }

    @Override
    public short getShortValue(int index) {
        Object item = list.get(index);
        if (item == null) {
            return 0;
        } else {
            return castToShort(item);
        }
    }

    @Override
    public Integer getInteger(int index) {
        Object item = list.get(index);
        return castToInt(item);
    }

    @Override
    public int getIntValue(int index) {
        Object item = list.get(index);
        if (item == null) {
            return 0;
        } else {
            return castToInt(item);
        }
    }

    @Override
    public Long getLong(int index) {
        Object item = list.get(index);
        return castToLong(item);
    }

    @Override
    public long getLongValue(int index) {
        Object item = list.get(index);
        if (item == null) {
            return 0;
        } else {
            return castToLong(item);
        }
    }

    @Override
    public Float getFloat(int index) {
        Object item = list.get(index);
        return castToFloat(item);
    }

    @Override
    public float getFloatValue(int index) {
        Object item = list.get(index);
        if (item == null) {
            return 0.0F;
        } else {
            return castToFloat(item);
        }
    }

    @Override
    public Double getDouble(int index) {
        Object item = list.get(index);
        return castToDouble(item);
    }

    @Override
    public double getDoubleValue(int index) {
        Object item = list.get(index);
        if (item == null) {
            return 0.0D;
        } else {
            return castToDouble(item);
        }
    }

    @Override
    public BigDecimal getBigDecimal(int index) {
        Object item = list.get(index);
        return castToBigDecimal(item);
    }

    @Override
    public BigInteger getBigInteger(int index) {
        Object item = list.get(index);
        return castToBigInteger(item);
    }

    @Override
    public String getString(int index) {
        Object item = list.get(index);
        return castToString(item);
    }

    @Override
    public Date getDate(int index) {
        Object item = list.get(index);
        return castToDate(item);
    }

    @Override
    public java.sql.Date getSqlDate(int index) {
        Object item = list.get(index);
        return castToSqlDate(item);
    }

    @Override
    public Timestamp getTimestamp(int index) {
        Object item = list.get(index);
        return castToTimestamp(item);
    }
}

package me.yufan.elasticsearch.common.json.gson;

import me.yufan.elasticsearch.common.json.JSONArray;
import me.yufan.elasticsearch.common.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

public class GsonJSONArray implements JSONArray {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Object> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Object e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Object> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return null;
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public JSONObject getJSONObject(int index) {
        return null;
    }

    @Override
    public JSONArray getJSONArray(int index) {
        return null;
    }

    @Override
    public <T> T getObject(int index, Class<T> clazz) {
        return null;
    }

    @Override
    public Boolean getBoolean(int index) {
        return null;
    }

    @Override
    public boolean getBooleanValue(int index) {
        return false;
    }

    @Override
    public Byte getByte(int index) {
        return null;
    }

    @Override
    public byte getByteValue(int index) {
        return 0;
    }

    @Override
    public Short getShort(int index) {
        return null;
    }

    @Override
    public short getShortValue(int index) {
        return 0;
    }

    @Override
    public Integer getInteger(int index) {
        return null;
    }

    @Override
    public int getIntValue(int index) {
        return 0;
    }

    @Override
    public Long getLong(int index) {
        return null;
    }

    @Override
    public long getLongValue(int index) {
        return 0;
    }

    @Override
    public Float getFloat(int index) {
        return null;
    }

    @Override
    public float getFloatValue(int index) {
        return 0;
    }

    @Override
    public Double getDouble(int index) {
        return null;
    }

    @Override
    public double getDoubleValue(int index) {
        return 0;
    }

    @Override
    public BigDecimal getBigDecimal(int index) {
        return null;
    }

    @Override
    public BigInteger getBigInteger(int index) {
        return null;
    }

    @Override
    public String getString(int index) {
        return null;
    }

    @Override
    public Date getDate(int index) {
        return null;
    }

    @Override
    public java.sql.Date getSqlDate(int index) {
        return null;
    }

    @Override
    public Timestamp getTimestamp(int index) {
        return null;
    }
}

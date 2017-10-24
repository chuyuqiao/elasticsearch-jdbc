package me.yufan.elasticsearch.common.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Wrapper Class for generic type
 */
public abstract class TypeReference<T> {

    private final Type type;

    public TypeReference() {
        Type superClass = getClass().getGenericSuperclass();

        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}

package me.yufan.elasticsearch.common.json.esjson.core.deserializer;

import java.lang.reflect.Type;

public interface Deserializer {

    <T> T deserialize(Object object, Type type);
}

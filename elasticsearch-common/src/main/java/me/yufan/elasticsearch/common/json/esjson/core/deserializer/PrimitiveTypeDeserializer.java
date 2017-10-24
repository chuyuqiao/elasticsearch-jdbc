package me.yufan.elasticsearch.common.json.esjson.core.deserializer;

import me.yufan.elasticsearch.common.utils.PrimitiveTypeUtils;

import java.lang.reflect.Type;

public class PrimitiveTypeDeserializer implements Deserializer {

    @SuppressWarnings("unchecked")
    public <T> T deserialize(Object object, Type type) {
        return PrimitiveTypeUtils.convert(object, type);
    }
}

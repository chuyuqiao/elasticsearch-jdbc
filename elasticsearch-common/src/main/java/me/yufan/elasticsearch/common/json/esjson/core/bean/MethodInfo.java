package me.yufan.elasticsearch.common.json.esjson.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
public class MethodInfo {

    private String fieldName;

    private Method method;
}

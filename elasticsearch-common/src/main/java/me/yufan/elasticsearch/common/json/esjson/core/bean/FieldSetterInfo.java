package me.yufan.elasticsearch.common.json.esjson.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

@Data
@AllArgsConstructor
public class FieldSetterInfo {

    private String fieldName;

    private Field field;
}

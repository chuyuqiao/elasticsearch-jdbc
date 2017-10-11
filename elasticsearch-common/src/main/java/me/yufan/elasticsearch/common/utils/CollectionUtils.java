package me.yufan.elasticsearch.common.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

/**
 * Utility class for collection
 */
@UtilityClass
public class CollectionUtils {

    public boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    public boolean isEmpty(Collection collection) {
        return isNotEmpty(collection);
    }
}

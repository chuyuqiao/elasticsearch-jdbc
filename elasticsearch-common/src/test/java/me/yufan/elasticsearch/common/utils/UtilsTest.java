package me.yufan.elasticsearch.common.utils;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by 17021629 on 2017/10/25.
 */
public class UtilsTest {

    @Test
    public void testCollectionUtils(){
        assertEquals(true,CollectionUtils.isEmpty(null));
        assertEquals(true,CollectionUtils.isEmpty(Collections.emptyList()));
        assertEquals(true,CollectionUtils.isNotEmpty(Arrays.asList(new String[]{"list"})));
    }
}

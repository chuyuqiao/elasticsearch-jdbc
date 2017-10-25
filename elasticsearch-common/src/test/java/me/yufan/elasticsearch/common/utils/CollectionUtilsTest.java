package me.yufan.elasticsearch.common.utils;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class CollectionUtilsTest {

    @Test
    public void testCollectionUtils(){
        assertThat(CollectionUtils.isEmpty(null), is(true));
        assertThat(CollectionUtils.isEmpty(emptyList()), is(true));
        assertThat(CollectionUtils.isEmpty(singletonList("some str")), is(true));
    }
}

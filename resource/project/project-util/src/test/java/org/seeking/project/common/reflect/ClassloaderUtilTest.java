package org.seeking.{{ project }}.common.reflect;

import org.junit.Test;

public class ClassloaderUtilTest {

    @Test
    public void test() {
        ClassLoader loader = ClassLoaderUtil.getDefaultClassLoader();
        ClassLoaderUtil.isPresent("org.seeking.{{ project }}.common.reflect.ClassUtil", loader);
    }
}

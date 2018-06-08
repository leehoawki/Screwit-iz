package org.seeking.{{ project }}.common.concurrent;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.seeking.{{ project }}.common.base.ObjectUtil;
import org.seeking.{{ project }}.common.base.RuntimeUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class ThreadUtilTest {
    @Test
    public void testCaller() {
        hello();
        new MyClass().hello();
        Assertions.assertThat(RuntimeUtil.getCurrentClass()).isEqualTo("org.seeking.{{ project }}.common.concurrent.ThreadUtilTest");
        assertThat(RuntimeUtil.getCurrentMethod()).isEqualTo("org.seeking.{{ project }}.common.concurrent.ThreadUtilTest.testCaller()");

    }

    private void hello() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        System.out.println(ObjectUtil.toPrettyString(stacktrace));

        assertThat(RuntimeUtil.getCallerClass()).isEqualTo("org.seeking.{{ project }}.common.concurrent.ThreadUtilTest");
        assertThat(RuntimeUtil.getCallerMethod()).isEqualTo("org.seeking.{{ project }}.common.concurrent.ThreadUtilTest.testCaller()");
    }

    public static class MyClass {
        public void hello() {
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            System.out.println(ObjectUtil.toPrettyString(stacktrace));

            assertThat(RuntimeUtil.getCallerClass()).isEqualTo("org.seeking.{{ project }}.common.concurrent.ThreadUtilTest");
            assertThat(RuntimeUtil.getCallerMethod()).isEqualTo("org.seeking.{{ project }}.common.concurrent.ThreadUtilTest.testCaller()");
        }
    }
}

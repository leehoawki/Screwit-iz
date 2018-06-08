package org.seeking.{{ project }}.common.concurrent;

import org.junit.Test;
import org.seeking.{{ project }}.common.concurrent.jsr166e.LongAdder;

import static org.assertj.core.api.Assertions.assertThat;

public class ConcurrentsTest {

    @Test
    public void longAdder() {
        LongAdder counter = Concurrents.longAdder();
        counter.increment();
        counter.add(2);
        assertThat(counter.longValue()).isEqualTo(3L);
    }

}

package org.seeking.{{ project }}.common.concurrent;

import org.junit.Test;
import org.seeking.{{ project }}.common.concurrent.threadpool.ThreadPoolBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadDumpperTest {

    public static class LongRunTask implements Runnable {

        private CountDownLatch countDownLatch;

        public LongRunTask(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
            ThreadUtil.sleep(5, TimeUnit.SECONDS);
        }
    }

    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
        CountDownLatch countDownLatch = Concurrents.countDownLatch(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(new LongRunTask(countDownLatch));
        }
        countDownLatch.await();

        ThreadDumpper dumpper = new ThreadDumpper();
        dumpper.threadDumpIfNeed();

        // disable,不输出
        dumpper.setEnable(false);
        dumpper.threadDumpIfNeed();

        // 设置最少间隔,不输出
        dumpper.setEnable(true);
        dumpper.setLeastInterval(1800);
        dumpper.threadDumpIfNeed();

        executor.shutdownNow();
    }
}

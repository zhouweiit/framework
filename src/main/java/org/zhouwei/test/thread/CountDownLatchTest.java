package org.zhouwei.test.thread;

import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhouwei on 2019/3/13
 **/
public class CountDownLatchTest {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start("a1");
            Thread.sleep(1000);
            stopWatch.stop();
            stopWatch.start("a2");
            Thread.sleep(300);
            stopWatch.stop();
            stopWatch.start("a3");
            Thread.sleep(500);
            stopWatch.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(stopWatch.prettyPrint());
        CountDownLatch latch = new CountDownLatch(3);
        try {
            System.out.println("all thread start");
            System.out.println("latch count:" + latch.getCount());

            //各自线程拿到latch后，在线程内部调用countDown，表示执行结束
            latch.countDown();
            latch.countDown();
            latch.countDown();

            System.out.println("latch count:" + latch.getCount());

            //主线程通过await阻塞，必须所有的线程调用countDown结束后，主线程才能继续
            latch.await();
            System.out.println("all thread finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

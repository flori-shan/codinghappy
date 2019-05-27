package cn.nihility.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yzx on 2019/5/23.
 */
public class CountDownLatchDemo {

    static class CountDownLatchInner implements Runnable {
        final static CountDownLatch end = new CountDownLatch(10);
        final static CountDownLatchInner demo = new CountDownLatchInner();

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(10) * 1000);
                System.out.println("check complete.");
                end.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class GameCountDown implements Runnable {
        final static CountDownLatch start = new CountDownLatch(10);
        final static CountDownLatch end = new CountDownLatch(10);

        @Override
        public void run() {

            try {
                Thread.sleep(new Random().nextInt(10) * 1000);
                start.countDown();
                System.out.println(Thread.currentThread().getName() + ": ready over.");

                start.await();

                Thread.sleep(new Random().nextInt(10) * 1000);
                System.out.println(Thread.currentThread().getName() + ": play over.");
                end.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(new GameCountDown(), "player:" + i);
        }
        GameCountDown.end.await();
        System.out.println("Fire!.......");
        exec.shutdown();
    }

    public static void main1(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(CountDownLatchInner.demo);
        }

        CountDownLatchInner.end.await();
        System.out.println("Fire!.......");
        exec.shutdown();

    }

}

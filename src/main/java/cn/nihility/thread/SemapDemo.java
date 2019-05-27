package cn.nihility.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by yzx on 2019/5/23.
 */
public class SemapDemo {

    static class SemapInner implements Runnable {
        final Semaphore semaphore = new Semaphore(5);
        @Override
        public void run() {
            try {
                /* 获取信号量 */
                semaphore.acquire();
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getId()+":done!");
                /* 注意要释放 */
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemapInner inner = new SemapInner();

        for (int i = 0; i < 20; i++) {
            exec.submit(inner);
        }
    }

}

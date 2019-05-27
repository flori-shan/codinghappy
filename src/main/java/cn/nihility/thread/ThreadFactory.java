package cn.nihility.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzx on 2019/5/24.
 */
public class ThreadFactory {

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(), (Runnable r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            System.out.println("create thread t " + t);
            return  t;
        });

        Thread task = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + " : thread id : " + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < 5; i++) {
            es.submit(task);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

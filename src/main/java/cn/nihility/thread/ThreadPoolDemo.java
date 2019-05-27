package cn.nihility.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yzx on 2019/5/23.
 */
public class ThreadPoolDemo {

    static class TaskFirst implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + " : Thread ID " + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TaskFirst tf = new TaskFirst();

        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            es.submit(tf);
        }

        es.shutdown();

    }

}

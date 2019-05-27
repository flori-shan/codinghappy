package cn.nihility.thread;

import java.util.concurrent.*;

/**
 * Created by yzx on 2019/5/23.
 */
public class ScheduleExecutorServiceDemo {

    static class DivTask implements Runnable {
        int a, b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double re = a / b;
            System.out.println(re);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new TraceTreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(new DivTask(100, i));
        }
    }

    public static void main2(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());

        for (int i = 0; i < 5; i++) {
            Future<?> future = poolExecutor.submit(new DivTask(100, i));
            future.get();
//            poolExecutor.execute(new DivTask(100, i));
        }


    }

    public static void main1(String[] args) {

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);

        ses.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS);

    }

}

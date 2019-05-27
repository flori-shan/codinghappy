package cn.nihility.thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by yzx on 2019/5/24.
 */
public class CountTask {

    static class CountTaskInner extends RecursiveTask<Long> {
        static final int THREAD_HOLD = 10000;
        long start;
        long end;

        public CountTaskInner(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            boolean canCompute = (end - start) < THREAD_HOLD;
            if (canCompute) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                long step = (start + end) / 100;
                System.out.println("step : " + step);
                ArrayList<CountTaskInner> subTasks = new ArrayList<>();
                long pos = start;
                for (int i = 0; i < 100; i++) {
                    long lastOne = pos + step;
                    if (lastOne > end) { lastOne = end; }
                    CountTaskInner subTask = new CountTaskInner(pos, lastOne);
                    pos += step + 1;
                    subTasks.add(subTask);
                    subTask.fork();
                }
                for (CountTaskInner task : subTasks) {
                    sum += task.join();
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTaskInner inner = new CountTaskInner(0, 200000L);

        ForkJoinTask<Long> result = forkJoinPool.submit(inner);

        try {
            Long res = result.get();
            System.out.println("result : " + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

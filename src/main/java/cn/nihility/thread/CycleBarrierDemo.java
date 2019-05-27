package cn.nihility.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by yzx on 2019/5/23.
 */
public class CycleBarrierDemo {

    static class CycleBarrierSoldier implements Runnable {
        String soldier;
        final CyclicBarrier cyclicBarrier;

        CycleBarrierSoldier(CyclicBarrier cyclic, String soliderName) {
            this.cyclicBarrier = cyclic;
            soldier = soliderName;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ":work done.");
        }
    }

    static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令:[士兵 " + N + " 个，任务完成.]");
            } else {
                System.out.println("司令:[士兵 " + N + " 个，集合完毕.]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));

        System.out.println("集合");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵 " + i + " 报道.");
            allSoldier[i] = new Thread(new CycleBarrierSoldier(cyclicBarrier, "士兵:" + i));
            allSoldier[i].start();

            if (i == 5) {
                allSoldier[i].interrupt();
            }
        }
    }

}

package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/21.
 */
public class PriorityDemo {

    static class HighPriority extends Thread {
        static int cnt = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    cnt++;
                    if (cnt > 100000000) {
                        System.out.println("HighPriority is complete.");
                        break;
                    }
                }
            }
        }
    }

    static class LowPriority extends Thread {
        static int cnt = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    cnt++;
                    if (cnt > 100000000) {
                        System.out.println("LowPriority is complete.");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        HighPriority ht = new HighPriority();
        LowPriority lt = new LowPriority();

        ht.setPriority(Thread.MAX_PRIORITY);
        lt.setPriority(Thread.MIN_PRIORITY);

        lt.start();
        ht.start();

    }

}

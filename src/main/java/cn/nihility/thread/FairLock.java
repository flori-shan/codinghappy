package cn.nihility.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yzx on 2019/5/23.
 */
public class FairLock {

    static class FairLockInner implements Runnable {
        static ReentrantLock fairLock = new ReentrantLock(/*true*/);

        @Override
        public void run() {
            while (true) {
                try {
                    fairLock.lock();
                    System.out.println(Thread.currentThread().getName() + ":get current lock.");
                } finally {
                    fairLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        FairLockInner fl = new FairLockInner();

        Thread t1 = new Thread(fl, "t1");
        Thread t2 = new Thread(fl, "t2");

        t1.start();t2.start();

    }
}

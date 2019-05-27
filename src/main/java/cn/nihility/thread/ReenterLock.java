package cn.nihility.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yzx on 2019/5/22.
 */
public class ReenterLock {

    static ReentrantLock lock = new ReentrantLock();
    static int count = 0;

    static class ReenterLockInner implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000000; i++) {
                lock.lock();
                try { count++; }
                finally { lock.unlock(); }
            }
        }
    }

    static class IntLock implements Runnable {

        static ReentrantLock lock1 = new ReentrantLock();
        static ReentrantLock lock2 = new ReentrantLock();
        int lock;

        IntLock(int lock) { this.lock = lock; }

        @Override
        public void run() {
            try {
                if (1 == lock) {
                    lock1.lockInterruptibly();
                    try { Thread.sleep(500); }
                    catch (InterruptedException e) { }
                    lock2.lockInterruptibly();
                } else {
                    lock2.lockInterruptibly();
                    try { Thread.sleep(500); }
                    catch (InterruptedException e) { }
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) { lock1.unlock(); }
                if (lock2.isHeldByCurrentThread()) { lock2.unlock(); }
                System.out.println(Thread.currentThread().getId() + " : Thread exit.");
            }
        }
    }

    static class TimeLock implements Runnable {
        static ReentrantLock lock = new ReentrantLock();
        @Override
        public void run() {
            try {
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println(System.currentTimeMillis() + " : entry");
                    Thread.sleep(6000);
                    System.out.println(System.currentTimeMillis() + " : out.");
                } else {
                    System.out.println("get lock failed.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }

        }
    }

    static class TryLock implements Runnable {
        static ReentrantLock lock1 = new ReentrantLock();
        static ReentrantLock lock2 = new ReentrantLock();
        int lock;
        TryLock(int lock) { this.lock = lock; }

        @Override
        public void run() {
            if (lock == 1) {
                while (true) {
                    if (lock1.tryLock()) {
                        try {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }
                            if (lock2.tryLock()) {
                                try {
                                    System.out.println(Thread.currentThread().getId() + ":1. My Job Done.");
                                    return;
                                } finally {
                                    lock2.unlock();
                                }
                            }
                        } finally {
                            lock1.unlock();
                        }
                    }
                }
            } else {
                while (true) {
                    if (lock2.tryLock()) {
                        try {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                            }

                            if (lock1.tryLock()) {
                                try {
                                    System.out.println(Thread.currentThread().getId() + ":2. My Job Done.");
                                    return;
                                } finally {
                                    lock1.unlock();
                                }
                            }

                        } finally {
                            lock2.unlock();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TryLock r1 = new TryLock(1);
        TryLock r2 = new TryLock(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();t2.start();
    }

    public static void main4(String[] args) {
        TimeLock tl = new TimeLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);

        t1.start();
        t2.start();
    }

    public static void main1(String[] args) throws InterruptedException {
        IntLock r1 = new IntLock(1);
        IntLock r2 = new IntLock(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();t2.start();
//        t1.join();t2.join();

        Thread.sleep(1000);

        t2.interrupt();

        System.out.println("exit.");
    }

    public static void main2(String[] args) throws InterruptedException {
        ReenterLockInner ti = new ReenterLockInner();

        Thread t1 = new Thread(ti);
        Thread t2 = new Thread(ti);

        t1.start();t2.start();
        t1.join();t2.join();

        System.out.println(count);
    }

}

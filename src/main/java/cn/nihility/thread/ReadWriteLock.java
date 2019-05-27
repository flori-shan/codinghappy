package cn.nihility.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yzx on 2019/5/23.
 */
public class ReadWriteLock {

    static class ReadWriteLockInner {
        static Lock lock = new ReentrantLock();
        static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        static Lock readLock = readWriteLock.readLock();
        static Lock writeLock = readWriteLock.writeLock();

        int value;

        public Object handleRead(Lock lock) {
            try {
                System.out.println(Thread.currentThread().getName() + ":handle otb read");
                lock.lock();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return value;
        }

        public void handleWrite(Lock lock, int index) {
            try {
                System.out.println(Thread.currentThread().getName() + ":handle otb write");
                lock.lock();
                Thread.sleep(1000);
                value = index;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockInner inner = new ReadWriteLockInner();
        Runnable readRunnable = () -> {
//            inner.handleRead(ReadWriteLockInner.readLock);
            inner.handleRead(ReadWriteLockInner.lock);
        };

        Runnable writeRunnable = () -> {
//          inner.handleWrite(ReadWriteLockInner.writeLock, new Random().nextInt());
          inner.handleWrite(ReadWriteLockInner.lock, new Random().nextInt());
        };
        long start  = System.currentTimeMillis();
        System.out.println("");

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable, "read:"+i).start();
        }
        for (int i = 0; i < 20; i++) {
            new Thread(writeRunnable, "write:"+i).start();
        }
        System.out.println("run time +"+ (System.currentTimeMillis() - start));
    }

}

package cn.nihility.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yzx on 2019/5/23.
 */
public class ReentrantLockCondition {

    static class ConditionInner implements Runnable {
        static ReentrantLock lock = new ReentrantLock();
        static Condition condition = lock.newCondition();

        @Override
        public void run() {
            try {
                lock.lock();
                condition.await();
                System.out.println("Thread is going on.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionInner ci = new ConditionInner();

        Thread t1 = new Thread(ci);

        t1.start();
        Thread.sleep(2000);

//        ConditionInner.lock.lock();
        ConditionInner.condition.signal();
//        ConditionInner.lock.unlock();

    }

}

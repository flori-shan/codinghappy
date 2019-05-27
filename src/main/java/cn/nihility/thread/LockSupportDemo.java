package cn.nihility.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by yzx on 2019/5/23.
 */
public class LockSupportDemo {

    static Object object = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1", 5);
    static ChangeObjectThread t2 = new ChangeObjectThread("t2", 3);

    static class ChangeObjectThread extends Thread {
        int second;
        ChangeObjectThread(String name, int second) { super.setName(name); this.second = second; }
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("in : " + getName());
                LockSupport.park();
                if (Thread.interrupted()) {
                    System.out.println(getName() + ":被中断了");
                }
                try {
                    Thread.sleep(second * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(getName() + ":execute over.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);

        t2.start();
        t1.interrupt();

//        LockSupport.unpark(t1);
        LockSupport.unpark(t2);

//        t1.join();t2.join();
    }
}

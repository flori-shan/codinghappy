package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/21.
 */
public class SimpleWaitNotify {

    final static Object object = new Object();

    static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start.");
                System.out.println(System.currentTimeMillis() + ":T1 wait for object.");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end.");
            }
        }
    }

    static class T2 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start, notify one thread.");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end.");

                /* 通知 T1 继续执行的时候 T1 并不能立即执行，必须得等 T2 释放 object 锁，在获取到 object 的锁资源，在继续执行
                * wait 会立即等待，但是它会释放 object 也就是当前锁对象的锁，让别的线程获取此资源
                * Thread.sleep 不会释放目标对象的锁资源
                * */

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();

        t1.start();
        t2.start();
    }

}

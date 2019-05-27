package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/21.
 */
public class SimpleSuspendResume {

    /* suspend 挂起，resume 继续执行 ， 不推荐使用 suspend 挂起线程
    * suspend 在挂起线程导致线程暂停的同时不会释放任何资源，就有可能导致其他要访问被它暂停使用的锁时都会被牵连
    * 而且只有对应的 resume 操作才可以唤醒，若 resume 在 suspend 之前执行，那么线程有可能永远不能被继续执行，
    * 最致命的是线程状态还是为 Runnable ，严重影响判断线程的状态
    * */

    final static Object object = new Object();
    static CObjectThread t1 = new CObjectThread("t1");
    static CObjectThread t2 = new CObjectThread("t2");


    static class CObjectThread extends Thread {
        CObjectThread(String name) {
            super.setName(name);
        }
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("in : " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    /* wait notify 实现 */
    static class CObject extends Thread {
        volatile boolean suspendme = false;

        public void suspendMe() {
            suspendme = true;
        }
        public void resumeMe() {
            suspendme = false;
            synchronized (this) {
                notify();
                System.out.println("notify");
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (suspendme) {
                        try {
                            System.out.println("wait");
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (object) {
//                    System.out.println("in CObject Thread.");
                }

                Thread.yield();
            }
        }
    }

    static class RObject extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (object) {
//                    System.out.println("In Read Thread.");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CObject t1 = new CObject();
        RObject t2 = new RObject();

        t1.start();
        t2.start();

        Thread.sleep(1000);

        t1.suspendMe();
        System.out.println("suspend t1 2 second.============================================");
        Thread.sleep(1000);

        System.out.println("resume t1.=======================================================");
        t1.resumeMe();
    }

    public static void main2(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();

        t1.resume();
        t2.resume();

        t1.join();
        t2.join();
    }

}

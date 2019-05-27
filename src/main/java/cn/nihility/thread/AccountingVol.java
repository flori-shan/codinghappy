package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/21.
 */
public class AccountingVol implements Runnable {

    static AccountingVol instance = new AccountingVol();
    volatile static int cnt = 0;

    synchronized void increase() {
        cnt++;
    }

    /* 线程 1/2 同时读取 cnt 为 0， 计算 cnt=1 并先后写入这个结果，虽然 cnt 被执行了增加两次，但是实际 cnt 仅增加了 1 */

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            /*synchronized (instance) {
                increase();
            }*/
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);*/

        Thread t1 = new Thread(new AccountingVol());
        Thread t2 = new Thread(new AccountingVol());

        t1.start();t2.start();
        t1.join();t2.join();

        System.out.println(cnt);
    }
}

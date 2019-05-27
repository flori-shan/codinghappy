package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/22.
 */
public class BadLockInteger implements Runnable {

    static Integer cnt = 0;
    static BadLockInteger instance = new BadLockInteger();

    /* Integer 是不可变量，对象一旦被创建，就不能被修改
    *  若：Integer 代表 1 那么它就永久代表 1，你永远不能修改 Integer 的值
    *  若需要新值 2 那就只能新建一个 Integer 对象
    * */

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            synchronized (instance) {
                /* cnt = Integer.valueOf(cnt.intValue() + 1) */
                cnt++;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();t2.start();
        t1.join();t2.join();

        System.out.println(cnt);

    }
}

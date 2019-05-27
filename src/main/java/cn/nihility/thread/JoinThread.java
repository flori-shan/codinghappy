package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/21.
 */
public class JoinThread {

    /*
    * 调用某个线程的这个方法时，这个方法会挂起调用线程，直到被调用线程结束执行，调用线程才会继续执行
    * 父线程等待子线程结束之后才能继续运行
    * */

    volatile static int cnt = 0;

    static class AddThread extends Thread {
        @Override
        public void run() {
            for (cnt = 0; cnt < 100000000; cnt++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();

        addThread.join();

        System.out.println(cnt);

    }

}

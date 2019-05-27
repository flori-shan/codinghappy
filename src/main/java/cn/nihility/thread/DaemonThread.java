package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/21.
 */
public class DaemonThread {

    /* 当系统仅有守护进程的时候那个此进程就会退出 */

    static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("Living");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
//        t.setDaemon(true); /* 程序会随 main 进程退出而退出 */
        t.start();

        Thread.sleep(3000);
    }

}

package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/21.
 */
public class InterruptedThead {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted.");
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted When Sleep.");
                    /* 在此异常当中会清除中断标志位，如果不在此处理，后续对中断标志位处理就可能会出错。 */
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                Thread.yield();
            }
        });

        t.start();
        Thread.sleep(3000);
        t.interrupt();

    }

}

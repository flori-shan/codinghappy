package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/23.
 */
public class TryReturn {

    public static void run() {
        try {
            System.out.println("first run.");

            try {
                System.out.println("second run.");
                return;
            } finally {
                System.out.println("second run finally.");
            }

        } finally {
            System.out.println("first run finally.");
        }

    }

    public static void main(String[] args) {
        run();
    }

}

package cn.nihility.thread;

/**
 * Created by yzx on 2019/5/20.
 */
public class StopThreadUnsafe {

    final static User user = new User();

    public static class User {
        int id;
        String name;

        public User() {
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class ChangeObjectThread extends Thread {

        volatile boolean stopme = false;

        public void stopMe() {
            stopme = true;
        }

        @Override
        public void run() {
            while (true) {
                if (stopme) {
                    System.out.println("Exit by stop me.");
                    break;
                }
                synchronized (user) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    user.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(v));
                }
            }
        }
    }

    static class ReadObjectThraed extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    if (user.getId() != Integer.parseInt(user.getName())) {
                        System.out.println("have different. user " + user);
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThraed().start();

        ChangeObjectThread t;
        while (true) {
            t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stopMe();
        }


    }

}

package cn.nihility.thread;

import jdk.nashorn.internal.scripts.JO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by yzx on 2019/5/21.
 */
public class ArrayListMultiThread {

    //    static ArrayList<Integer> list = new ArrayList<>(64);
    static Vector<Integer> list = new Vector<>(64);
    static Map<String, String> map = new HashMap<>();

    static class AddListThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
        }
    }

    static class AddMapThread implements Runnable {
        int start = 0;
        AddMapThread(int start) { this.start = start; }
        @Override
        public void run() {
            for (int i = start; i < 100000; i++) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    static class JoinThread extends Thread {
        int second;

        JoinThread(int second, String name) {
            super.setName(name);
            this.second = second;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(second * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : end.");
        }
    }

    public static void main2(String[] args) throws InterruptedException {
        Thread t1 = new JoinThread(3, "t1");
        Thread t2 = new JoinThread(1, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("=========================");

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddMapThread(0));
        Thread t2 = new Thread(new AddMapThread(1));

        t1.start();t2.start();
        t1.join();t2.join();

        System.out.println(map.size());
    }

    public static void main1(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddListThread());
        Thread t2 = new Thread(new AddListThread());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(list.size());
    }


}

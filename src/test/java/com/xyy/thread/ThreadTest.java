package com.xyy.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author xuyy
 * @date 2018/9/6 19:01
 */
public class ThreadTest {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("ddd1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join(5000);
            System.out.println("ddd1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

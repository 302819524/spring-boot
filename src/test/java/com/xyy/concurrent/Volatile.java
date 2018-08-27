package com.xyy.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author xuyy
 * @date 2018/8/27 14:40
 */
public class Volatile {
    private volatile int status = 0;

    @Test
    public void maine() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int t = getStatus();
                int v = t + 1;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1-"+v);
                setStatus(v);
                System.out.println("1-"+v);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int t = getStatus();
                int v = t + 1;
                System.out.println("2-"+v);
                setStatus(v);
                System.out.println("2-"+v);
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getStatus());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

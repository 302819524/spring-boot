package com.xyy.thread;

import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

/**
 * @author xuyy
 * @date 2018/9/12 19:39
 */
public class StampedLockTest {
    private StampedLock lock = new StampedLock();

    @Test
    public void test1(){
        long stamp = lock.writeLock();
        long stamp2 = lock.writeLock();
        lock.unlockWrite(stamp);
    }
}

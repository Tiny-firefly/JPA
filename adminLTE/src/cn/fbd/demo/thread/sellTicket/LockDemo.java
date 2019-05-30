package cn.fbd.demo.thread.sellTicket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全问题演示，卖票示例   使用Lock锁
 * java.util.concurrent.locks.Lock接口
 * Lock实现提供了比使用synchronized方法和语句 获得的更广泛的锁定操作
 *     Lock接口中的方法：
 *          void lock() 获取锁
 *          void unlock() 释放锁
 * java.util.concurrent.locks.ReentrantLock implement Lock接口
 *
 * 使用步骤：
 *      1. 在成员位置创建一个ReentrantLock对象
 *      2. 在可能出现线程安全问题的代码 前 调用Lock接口中的方法lock获取锁
 *      3. 在可能出现线程安全问题的代码 后 调用Lock接口中的方法unlock释放锁。
 */
public class LockDemo implements Runnable{

    // 定义一个多个线程共享的票源
    private int ticket = 1000;
    // 1. 在成员位置创建一个ReentrantLock对象
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        //判断是否有票
        while(ticket>0){
            // 注意 这个同步快要放到循环的判断的下边
            // 2. 在可能出现线程安全问题的代码 前 调用Lock接口中的方法lock获取锁
            lock.lock();
                if(ticket>0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"正在买第："+ticket+"张票！");
                    ticket--;
                }
                //3. 在可能出现线程安全问题的代码 后 调用Lock接口中的方法unlock释放锁。
                lock.unlock();
        }

    }
}

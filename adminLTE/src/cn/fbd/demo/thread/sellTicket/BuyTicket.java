package cn.fbd.demo.thread.sellTicket;

import org.junit.Test;

public class BuyTicket {


    public static void main(String[] args){
        // 创建Runnable接口实现类对象，卖票
        Runnable run = new SellTicket();
        // 创建Thread类对象，构造方法中传递Runnable接口实现类对象
        Thread t0 = new Thread(run);
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        // 调用start() 方法开启线程
        t0.start();
        t1.start();
        t2.start();
    }

    @Test
    public void  lockTest() throws InterruptedException {
        // 创建Runnable接口实现类对象，卖票
        Runnable run = new LockDemo();
        // 创建Thread类对象，构造方法中传递Runnable接口实现类对象
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        Thread t3 = new Thread(run);
        // 调用线程的start方法 开启线程
        t1.start();
        t2.start();
        t3.start();
        //在junit单元测试中，为防止主线程停止导致整个程序停止
        // 先让主线程睡眠，时间大概能支持其他测试的线程执行完就可以
        Thread.sleep(1000*10);
    }

}

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

}

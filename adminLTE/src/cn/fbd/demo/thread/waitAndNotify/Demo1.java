package cn.fbd.demo.thread.waitAndNotify;

import static java.lang.Thread.*;

/**
 * 多线程： 等待与唤醒机制演示
 *
 * Object 类中的方法
 * void wait()
 *      在其他线程调用测对象的notify() 方法或notifyAll()方法前，导致当前线程等待。
 * void notify()
 *      唤醒在此对象监视器上等待的单个线程
 *      会继续执行wait()方法之后的代码
 */
public class Demo1 {
    // 定义交易 次数
    static Integer  count = 3;
    public static void main(String[] args) {


        //创建线程锁对象，保证唯一
        Object obj = new Object();
        // 创建一个顾客线程 -- 消费者
        new Thread(){
            @Override
            public void run() {

               while(count > 0){
                   // 保证等待和唤醒的线程只能有一个执行，需要使用同步技术
                   synchronized(obj){
                       System.out.println("顾客： 订货500件！");
                       // 调用wait()方法，放弃cpu的执行，进入到waiting状态（无限等待）
                       try {
                           obj.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       // 唤醒之后执行 的代码
                       System.out.println("顾客： 取货完成！");
                   }
                   count--;
               }

            }
        }.start();


        // 创建一个加工厂线程 -生产者
        new Thread(){
            @Override
            public void run() {

                while(count>0){
                    //花了5秒 ，生产商品
                    try {
                        Thread.sleep(5*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 保证等待和唤醒的线程只能有一个执行，需要使用同步技术
                    synchronized (obj){
                        System.out.println("厂家： 产品生产完成，交货！");
                        // 唤醒顾客，来取货
                        obj.notify();
                    }
                }

            }
        }.start();

    }


}

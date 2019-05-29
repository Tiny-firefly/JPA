package cn.fbd.demo.thread.sellTicket;

/**
 * 线程安全问题演示，卖票示例
 */
public class SellTicket implements Runnable{

// 定义一个多个线程共享的票源
    private int ticket = 10000;

    // 创建一个锁对象
    Object obj = new Object();
    @Override
    public void run() {


        // 同步代码块
        /**
         * 格式： synchronized(锁对象){
         * }
         *
         * 注意：
         *   1. 通过代码块中的锁对象，可以使用任意的对象
         *   2. 必须保证多个线程是哦那个的锁对象是同一个
         *   3. 锁对象作用：
         *          把同步代码块索住，只让一个线程在同步代码中执行
         *   4. 锁对象要在run方法之外创建
         */

        // 同步代码块

            //判断是否有票
            while(ticket>0){
                // 注意 这个同步快要放到循环的判断的下边
                synchronized(obj){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"正在买第："+ticket+"张票！");
                ticket--;
            }
        }

    }
}

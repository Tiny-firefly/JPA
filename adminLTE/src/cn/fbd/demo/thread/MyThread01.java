package cn.fbd.demo.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义线程类  方式一： 继承Thread 类
 * Thread类run方法中的任务并不是我们所需要的，
 * 只有重写这个run方法。既然Thread类已经定义了线程任务的编写位置（run方法），
 * 那么只要在编写位置（run方法）中定义任务代码即可。所以进行了重写run方法动作
 */
public class MyThread01 extends Thread {

    public MyThread01(String name) {
        //调用父类的String参数的构造方法，指定线程的名称
        super(name);
    }

    /**
     * 重写run方法，完成该线程执行的逻辑
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + "：正在执行！" + i);
            System.out.println(Thread.currentThread().getName()+",i="+i);
        }
    }

}

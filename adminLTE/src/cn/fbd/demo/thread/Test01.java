package cn.fbd.demo.thread;

import org.testng.annotations.Test;

public class Test01 {

    @Test
    // 继承Thread类方式
    public void test1() {
        //创建自定义线程对象
        MyThread01 mt = new MyThread01("新的线程！");
        //开启新线程
        mt.start();
        //在主方法中执行for循环
        for (int i = 0; i < 10; i++) {
            System.out.println("main线程！" + i);
        }
    }

    @Test
    // 实现runnable方式
    public void test2() {
        //创建线程执行目标类对象
        Runnable runn = new MyThread02();
        //将Runnable接口的子类对象作为参数传递给Thread类的构造函数
        Thread thread = new Thread(runn);
        Thread thread2 = new Thread(runn);
        //开启线程
        thread.start();
        thread2.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main线程：正在执行！");
        }
    }

    @Test
    // 线程的匿名内部类- 1. 直接重写Thread类中的run方法
    public void test3(){
        new Thread("线程111"){
            public void run(){
                for (int i = 0; i <100 ; i++) {
                    Thread.currentThread().setName("runnable线程");
                    System.out.println(getName()+"---"+i);
                }
            }
        }.start();
        for (int i = 0; i <100 ; i++) {
            System.out.println("main 线程！");
        }

    }

    @Test
    // 线程的匿名内部类- 2. 实现Runnable接口，重新Runnable接口中的run方法
    public void test4(){

        Runnable runn = new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i <100 ; i++) {
                    Thread.currentThread().setName("runnable线程");
                    System.out.println(Thread.currentThread().getName()+"---"+i);
                }
            }
        };
        new Thread(runn).start();


    }

}

package cn.fbd.demo.thread;

/**
 * 方式二： 声明实现 Runnable 接口
 * 实现Runnable接口，避免了继承Thread类的单继承局限性。
 */
public class MyThread02 implements Runnable{


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("实现Runnable方式-线程：正在执行！"+i);
        }

    }
}

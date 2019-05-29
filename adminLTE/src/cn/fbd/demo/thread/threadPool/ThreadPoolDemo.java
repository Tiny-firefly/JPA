package cn.fbd.demo.thread.threadPool;

import cn.fbd.demo.thread.MyThread01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolDemo {

    public static void main(String[] args)throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(2);
        //提交线程任务的方法submit方法返回 Future接口的实现类
        Future<String> f = (Future<String>) es.submit(new MyThread01("hahaha"));
        String s = f.get();
        System.out.println(s);
    }



}

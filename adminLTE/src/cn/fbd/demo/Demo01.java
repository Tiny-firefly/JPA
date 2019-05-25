package cn.fbd.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Demo01 {
    @Test
    public  void first(){
        System.out.println("正在测试！！！");
    }
    @Before
    public void init(){
        System.out.println("测试初始化！");
    }

    @After
    public void end(){
        System.out.println("测试结束，资源释放！");
    }
}

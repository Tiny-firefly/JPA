package cn.fbd.demo.reflectDemo;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 反射框架 小示例
 */
public class ReflectTest {

    @Test
    public void Test() throws Exception  {
        // 不改变代码可以创建任意类的对象，可以执行任意方法
        // 1. 加载配置文件
        // 1.1 创建properties对象
        Properties pro = new Properties();
        //1.2 加载配置文件，转换为一个集合
        // 1.2.1 获取class目录下的配置文件
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("reflect.properties");
        pro.load(is);
        // 2. 获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");
        System.out.println(className + "--" + methodName);

        // 3. 加载该类仅内存
        Class cls = Class.forName(className);
        // 4. 创建对象
        Object obj = cls.newInstance();
        // 5. 获取方法对象
        Method method = cls.getMethod(methodName);
        // 6.执行方法  无参
        method.invoke(obj);
        //  执行有参 方法
        Method method2 = cls.getMethod(methodName,String.class);
        method2.invoke(obj,"红晓实施");
    }

}

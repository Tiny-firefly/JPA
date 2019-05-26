package cn.fbd.demo.reflectDemo;


import cn.fbd.demo.reflectDemo.domain.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class reflectClassTest {

    // 获取Class对象的三种方式
    @Test
    public void test01() throws ClassNotFoundException {
        //1. Class.forName("全类名")
        Class cls1 = Class.forName("cn.fbd.demo.reflectDemo.domain.Person");
        System.out.println(cls1);
        //2. 类名.class 属性
        Class cls2 = Person.class;
        System.out.println(cls2);
        // 3. 对象.getClass() 方法
        Person p = new Person();
        Class cls3 = p.getClass();
        System.out.println(cls3);

        System.out.println(cls1==cls2);
        System.out.println(cls1==cls3);
    }

    /**
     *
     */
    @Test
     public void test02() throws NoSuchFieldException, IllegalAccessException {
         // 获取Persion的Class对象
         Class persionClass = Person.class;
         /**
          * 1. 获取成员变量  public修饰的成员变量
          *     Field[] getFields()    所有的
          *     Field getField(String name) 指定名称的
          *
          *     Declared 暴力获取所有修饰符的成员变量
          *     Field[] getDeclaredFields()
          *     Field[] getDeclareField(String name)
          */
         // 获取所有public修饰的成员变量
         Field[] fields = persionClass.getFields();
         for (Field field: fields
              ) {
             System.out.println(field);
         }
        // 获取指定名称的public修饰的成员变量
         Field a = persionClass.getField("a");
         System.out.println(a);

         // 获取所有修饰符类型的成员变量
        Field[] allFields = persionClass.getDeclaredFields();
        for (Field fields2: allFields
             ) {
            System.out.println(fields2);
        }
        // 获取指定名称修饰符类型的成员变量
        Field a2 = persionClass.getDeclaredField("name");

        System.out.println(a2);
        // 在获取或者设置值之前要设置忽略访问权限修饰符的安全检查
        a2.setAccessible(true);
        //----------
        /**
         * 对成员变量Field的操作
         *  1. 设置值
         *     void set(Object obj,Object value)
         *  2. 获取值
         *     get(Object obj)
         *  3. 忽略访问修饰符的安全检查
         *      setAccessible(true)   暴力反射
         */
        // 获取成员变量的值
        Person p = new Person();
        Object obj = a2.get(p);
        System.out.println(obj);
        // 设置成员变量的值
        a2.set(p,"嘿嘿");
        System.out.println(p);

    }

    /**
     * 获取构造方法
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        Class personClass = Person.class;
        // 获取构造方法 有参构造
        Constructor construtor1 = personClass.getConstructor(String.class,Integer.class,String.class);
        System.out.println(construtor1);
        // 创建对象
        Object persion = construtor1.newInstance("老王", 23, "老铁");
        System.out.println(persion);
        System.out.println("=======================");
        // 获取构造方法 无参构造
        Constructor construtor2 = personClass.getConstructor();
        System.out.println(construtor2);
        // 创建对象
        Object persion2 = construtor2.newInstance();
        System.out.println("常规的方式获取空参构造方法： "+persion2);
        // 使用简化的方式获取空参构造方法
        Object o = personClass.newInstance();
        System.out.println("简化的方式获取空参构造方法： "+o);
    }

    /**
     * // 获取成员方法
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void test04() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class person = Person.class;
        Method eat_method = person.getMethod("eat");
        // 执行无参成员方法
        Person p = new Person();
        eat_method.invoke(p);

        // 执行有参数的成员方法
        Method eat_method2 = person.getMethod("eat",String.class);
        eat_method2.invoke(p,"火鸡面！");

        //-------------------
        //获取所有public修饰的方法
        System.out.println("------------------------");
        Method[] methods = person.getMethods();
        for (Method method : methods
             ) {
            System.out.println(method);
            // 获取方法名
            System.out.println(method.getName());
            //method.setAccessible(true);
        }

    }



}

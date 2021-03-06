package com.jpa.test;

import com.jpa.domain.Customer;
import com.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaZSGCTest {
    /**
     * 测试jpa的保存，+ 自动创建数据库表
     * 案例： 保存一个客户到数据库中
     * Jpa的操作步骤
     * 1. 加载配置文件创建工厂（实体管理工厂）对象
     * 2. 通过实体管理器工厂 或实体管理器
     * 3. 获取事务对象，开启事务
     * 4. 完成增删该查操作
     * 5. 提交事务（回滚事务）
     * 6. 释放资源
     */
    @Test
    /**
     * 1.保存
     */
    public void testSave() {
        //1.获取实体管理器
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 2.1 开启事务
        transaction.begin();
        //3.完成增删改查操作：保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("老鹰");
        customer.setCustIndustry("吃小鸡");
        //4. 保存
        entityManager.persist(customer);
        //5. 提交事务
        transaction.commit();
        //6. 释放资源
        entityManager.close();
    }

    /**
     * 2. 根据id查询客户
     *
     * 使用find方法查询：
     * 1.查询的对象就是当前客户对象本身
     * 2.在调用find方法的时候，就会发送sql语句查询数据库
     * 立即加载
     */
    @Test
    public void testFind() {
        //1.通过工具类获取entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.增删改查 -- 根据id查询客户
        /**
         * find : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customer = em.find(Customer.class, 1L);
        System.out.println(customer);
        // 4.提交事务
        tx.commit();
        // 5.释放资源
        em.close();
    }

    /**
     *   3. 根据id查询客户   懒加载的方式
     *      getReference方法
     *          1.获取的对象是一个动态代理对象
     *          2.调用getReference方法不会立即发送sql语句查询数据库
     *              * 当调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询数据库
     *
     * 延迟加载（懒加载）
     *      * 得到的是一个动态代理对象
     *      * 什么时候用，什么使用才会查询
     */
    @Test
    public  void testReference() {
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.增删改查 -- 根据id查询客户
        /**
         * getReference : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customerReference = entityManager.getReference(Customer.class, 3l);
        System.out.println(customerReference);
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 4.删除客户的案例
     *
     */
    @Test
    public  void testRemove() {
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.增删改查 -- 删除客户
        //i 根据id查询客户
        Customer customer = entityManager.find(Customer.class,2l);
        //ii 调用remove方法完成删除操作
        entityManager.remove(customer);
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 更新客户的操作
     *      merge(Object)
     */
    @Test
    public  void testUpdate() {
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.增删改查 -- 更新操作

        //i 查询客户
        Customer customer = entityManager.find(Customer.class,1l);
        //ii 更新客户
        customer.setCustIndustry("如果我是dj");
        entityManager.merge(customer);

        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }
}

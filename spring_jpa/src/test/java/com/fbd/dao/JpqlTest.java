package com.fbd.dao;

import com.fbd.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

//声明spring提供的单元测试环境
@RunWith(SpringJUnit4ClassRunner.class)
// 指定spring的配置信息
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJPQL() {
        Customer customer = customerDao.findJpal("老鹰");
        System.out.println(customer);
    }

    @Test
    public void testFindCustNameAndId() {
        // Customer customer =  customerDao.findCustNameAndId("传智播客",1l);
        Customer customer = customerDao.testFindCustNameAndId(6l, "老鹰");
        System.out.println(customer);
    }
    /**
     * 测试jpql的更新操作
     *  * springDataJpa中使用jpql完成 更新/删除操作
     *         * 需要手动添加事务的支持
     *         * 默认会执行结束之后，回滚事务
     *   @Rollback : 设置是否自动回滚
     *          false | true
     */
    @Test
    @Transactional //添加事务的支持
    @Rollback(value = false)
    public void testUpdateCustomer() {
        customerDao.updateCustomer(4l,"教父");
    }
    // 测试 原生sql查询
    @Test
    public void testFindSql() {
        List<Object[]> list = customerDao.findSql("老%");
        for (Object[] obj : list
                ) {
            System.out.println(Arrays.toString(obj));
        }
    }

    /**
     * ----------------约定方法名 进行查询--------------------
     */
    @Test
    public void testNaming(){
        Customer customer = customerDao.findByCustName("泰戈尔");
        System.out.println(customer);
    }

    //测试方法命名规则的查询
    @Test
    public void testFindByCustNameLike() {
        List<Customer> list = customerDao.findByCustNameLike("老%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    //测试方法命名规则的查询
    @Test
    public void testFindByCustNameLikeAndCustIndustry() {
        Customer customer = customerDao.findByCustNameLikeAndCustIndustry("老%", "吃小鸡");
        System.out.println(customer);
    }


}

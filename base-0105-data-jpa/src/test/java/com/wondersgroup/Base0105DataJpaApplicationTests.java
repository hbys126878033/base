package com.wondersgroup;

import com.wondersgroup.dao.UserDao;
import com.wondersgroup.dao.UserJapDap;
import com.wondersgroup.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Base0105DataJpaApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserJapDap userJapDao;

    @Test
    void contextLoads() {
        /** 查询所有的数据*/

        System.out.println("--------查询所有的数据--------");
        Iterable<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }

        System.out.println("--------根据ID查询--------");
        /**根据ID查询具体的数据，Optional  是jdk1.8才执行的，作用是避免空指针异常*/
        Optional<User> byId = userDao.findById(1L);
        if(byId.isPresent()){
            System.out.println(byId.get());
        }

        System.out.println("--------自定义查询(根据方法名称)--------");
        List<User> users = userDao.findByName("chenlin");
        for (User user : users) {
            System.out.println(user);

        }
        System.out.println("--------自定义查询(根据注解Query)--------");
        List<User> users2 = userDao.find("chenlin");
        for (User user : users2) {
            System.out.println(user);
        }

        System.out.println("--------自定义查询(根据注解Query)--------");
        List<User> users3 = userDao.findName("Jone");
        for (User user : users3) {
            System.out.println(user);
        }

        System.out.println("--------自定义查询(根据注解Query)--------");
        User u = new User();
        u.setName("Jone");
        u.setEmail("8888");
        Iterable<User> user4 = userDao.findByNameOrEmail(u);
        for (User user : user4) {
            System.out.println(user);
        }

    }


    @Test
    @Transactional
    public void testJpaRepository(){
        User one = userJapDao.getOne(1L);
        System.out.println(one);
        userJapDao.findById(1L);
        userJapDao.findAll();
    }
}

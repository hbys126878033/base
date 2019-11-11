package com.wondersgrop;

import com.wondersgrop.model.mapper.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Base0501CacheApplicationTests {

    @Autowired
    private UserDao userDao;

    // 有效时间是5秒，第一次和第二次获取的值是一样的，因第三次是5秒之后所以会获取新的值
    @Test
    public void testTimestamp() throws InterruptedException {
        System.out.println("第一次调用：" + userDao.getTimestamp("param"));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + userDao.getTimestamp("param"));
        Thread.sleep(4000);
        System.out.println("再过4秒之后调用：" + userDao.getTimestamp("param"));
    }

    @Test
    public void testCache() {
        String key = "zhangsan";
        String value = userDao.getDataFromDB(key); // 从数据库中获取数据...
        userDao.getDataFromDB(key);  // 从缓存中获取数据，所以不执行该方法体
        userDao.removeDataAtDB(key); // 从数据库中删除数据
        userDao.getDataFromDB(key);  // 从数据库中获取数据...（缓存数据删除了，所以要重新获取，执行方法体）
    }

    @Test
    public void testPut() {
        String key = "mengdee";
        userDao.refreshData(key);  // 模拟从数据库中加载数据
        String data = userDao.getDataFromDB(key);
        System.out.println("data:" + data); // data:mengdee::103385

        userDao.refreshData(key);  // 模拟从数据库中加载数据
        String data2 = userDao.getDataFromDB(key);
        System.out.println("data2:" + data2);   // data2:mengdee::180538
    }


    @Test
    public void testFindById() {
        userDao.findById("1"); // 模拟从数据库中查询数据
        userDao.findById("1");
    }

    @Test
    public void testIsReserved() {
        userDao.isReserved("123");
        userDao.isReserved("123");
    }

    @Test
    public void testRemoveUser() {
        // 线添加到缓存
        userDao.findById("1");

        // 再删除
        userDao.removeUser("1");

        // 如果不存在会执行方法体
        userDao.findById("1");
    }

    @Test
    public void testRemoveAllUser() {
        userDao.findById("1");
        userDao.findById("2");

        userDao.removeAllUser();

        userDao.findById("1");
        userDao.findById("2");

//      模拟从数据库中查询数据
//      模拟从数据库中查询数据
//      UserCache delete all
//      模拟从数据库中查询数据
//      模拟从数据库中查询数据
    }

}

package com.wondersgrop;

import com.wondersgrop.model.entity.SysUser;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenlin
 * @create 2019-06-24 12:52
 * @description: TODO
 * @version：1.0
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest

public class CacheTest {


    @Test
    public void test() {

        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");

        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("user");

        // 3. 创建元素
        Element element = new Element("key1", "value1");

        // 4. 将元素添加到缓存
        cache.put(element);

        // 5. 获取缓存
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());

        // 6. 删除元素
        cache.remove("key1");

        SysUser dog = new SysUser();
        dog.setLoginName("admin");
        Element e = new Element("dog", dog);

        cache.put(e);

        Element ee = cache.get("dog");
        System.out.println(ee);
        System.out.println(ee.getObjectValue());
        System.out.println(cache.getSize());

        // 7. 刷新缓存
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();
    }
}

package com.wondersgrop.model.mapper;

import com.wondersgrop.model.entity.SysUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author chenlin
 * @create 2019-06-24 12:43
 * @description: 缓存测试
 * @CacheConfig 可以指定只用那个缓存的节点
 * @Cacheable 表明所修饰的方法是可以缓存的：当第一次调用这个方法时，它的结果会被缓存下来，
 * 在缓存的有效时间内，以后访问这个方法都直接返回缓存结果，不再执行方法中的代码段。
 * @CachePut 与@Cacheable不同，@CachePut不仅会缓存方法的结果，还会缓存执行方法的代码段。它支持的属性和用法都与@Cacheable一致。
 * @CacheEvict 与@Cacheable功能相反，@CacheEvict表明所修饰的方法是用来删除失效或无用的缓存数据。
 * <p>
 * 如果和接入SpringBoot，需要在配置类中加上@EnableCaching的注解，不然缓存不会生效
 * @version：1.0
 **/
@Repository
@CacheConfig(cacheNames = "user")
public class UserDao {


    @Cacheable
    public SysUser query(Long id) {
        SysUser user = new SysUser(id, "admin");
        user.setCreateTime(new Date());
        return user;
    }

    // value的值和ehcache.xml中的配置保持一致
    @Cacheable(value = "HelloWorldCache", key = "#param")
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

    @Cacheable(value = "HelloWorldCache", key = "#key")
    public String getDataFromDB(String key) {
        System.out.println("从数据库中获取数据...");
        return key + ":" + String.valueOf(Math.round(Math.random() * 1000000));
    }

    @CacheEvict(value = "HelloWorldCache", key = "#key")
    public void removeDataAtDB(String key) {
        System.out.println("从数据库中删除数据");
    }

    @CachePut(value = "HelloWorldCache", key = "#key")
    public String refreshData(String key) {
        System.out.println("模拟从数据库中加载数据");
        return key + "::" + String.valueOf(Math.round(Math.random() * 1000000));
    }

    // ------------------------------------------------------------------------
    @Cacheable(value = "UserCache", key = "'user:' + #userId")
    public SysUser findById(String userId) {
        System.out.println("模拟从数据库中查询数据");
        return (SysUser) new SysUser(1L, "mengdee");
    }

    @Cacheable(value = "UserCache", condition = "#userId.length()<12")
    public boolean isReserved(String userId) {
        System.out.println("UserCache:" + userId);
        return false;
    }

    //清除掉UserCache中某个指定key的缓存
    @CacheEvict(value = "UserCache", key = "'user:' + #userId")
    public void removeUser(String userId) {
        System.out.println("UserCache remove:" + userId);
    }

    //清除掉UserCache中全部的缓存
    @CacheEvict(value = "UserCache", allEntries = true)
    public void removeAllUser() {
        System.out.println("UserCache delete all");
    }


}

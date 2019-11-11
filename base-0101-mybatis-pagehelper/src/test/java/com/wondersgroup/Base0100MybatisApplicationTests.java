package com.wondersgroup;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wondersgroup.model.entity.SysUser;
import com.wondersgroup.model.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Base0100MybatisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private SysUserMapper sysUserMapper;


    @Test
    public void test01() {
        System.out.println("-----------------------------------test01------------------------------------------");
        SysUser user = new SysUser();
        user.setLoginName("admin");
        user.setName("管理员");
        user.setPassword("admin");
        user.setStatus("1");
        user.setInternal("1");
        user.setUserOrder(1);
        user.setCreateId(1L);
        user.setCreateTime(new Date());

        // sysUserMapper.deleteByPrimaryKey(1L);
        sysUserMapper.insert(user);
        sysUserMapper.insertSelective(user);
        SysUser u = sysUserMapper.selectByPrimaryKey(35L);
        System.out.println("u = " + u);
    }

    @Test
    public void testQuery() {
        PageHelper.startPage(1, 10);
        Page<SysUser> p = sysUserMapper.query();
        System.out.println(p.getPageSize());
        System.out.println(p.getPageNum());
        System.out.println(p.getPages());
        System.out.println(p.size());
        System.out.println(p);
        for (int i = 0, length = p.size(); i < length; i++) {
            System.out.println(p);
            System.out.println("----------------");
            System.out.println(p.getTotal());
            System.out.println(p.get(0));

        }
    }

}

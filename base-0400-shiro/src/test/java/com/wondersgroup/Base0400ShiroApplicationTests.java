package com.wondersgroup;

import com.wondersgroup.model.entity.SysUser;
import com.wondersgroup.model.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.wondersgroup.model.mapper")
public class Base0400ShiroApplicationTests {

    @Autowired
    SysUserService sysUserService;

    @Test
    public void addUser() {
        SysUser user = new SysUser();
        user.setLoginName("admin1255533388992");
        user.setName("超级管理员");
        user.setPassword("admin");
        user.setStatus("1");
        user.setInternal("1");
        user.setCreateTime(new Date());
        user.setCreateBy(1L);
        user.setUserOrder(1);
        sysUserService.addUser(user);
    }

}

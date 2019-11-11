package com.wondersgroup;

import com.wondersgroup.model.entity.SysUser;
import com.wondersgroup.model.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Base01MybatisApplicationTests {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testUserInsert() {
        SysUser user = new SysUser();
        user.setLoginName("admin");
        user.setName("管理员");
        user.setPassword("admin");
        user.setStatus("1");
        user.setInternal("0");
        user.setUserOrder(1);
        user.setCreateId(1L);
        user.setCreateTime(new Date());
        sysUserMapper.insertSelective(user);


        List<SysUser> query = sysUserMapper.query();
        for (SysUser user1 :
                query) {
            System.out.println(user1);

        }
    }


}

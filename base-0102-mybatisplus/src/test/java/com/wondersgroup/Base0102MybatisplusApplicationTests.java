package com.wondersgroup;

import com.wondersgroup.model.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(value = "com.wondersgroup.model.mapper")
public class Base0102MybatisplusApplicationTests {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testPlus() {
        sysUserMapper.query();
    }

}

package com.wondersgroup;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wondersgroup.entity.User;
import com.wondersgroup.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Base0104MybatisPlusAndPagehelperApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
      /*  User u = insert();
        System.out.println(u);

        User uu = selectById(u.getId());
        System.out.println(uu);

        uu.setEmail("8888");
        User uuu = update(uu);
        System.out.println(uuu);*/

        PageHelper.startPage(1,2);
        List<User> users = userMapper.queryForPage();
        System.out.println(users.size());
        System.out.println(users);

        PageInfo page = new PageInfo(users);
        System.out.println(page.getTotal());
        System.out.println(page.getList().get(0));


    }


    public User selectById(Long id){
        return userMapper.selectById(id);
    }

    public User insert(){
        User u = new User();
        u.setName("chenlin");
        u.setAge(30);
        u.setEmail("chenlin@wondersgroup.com");
        userMapper.insert(u);
        return u;
    }

    public User update(User u){
        userMapper.updateById(u);
        return selectById(u.getId());
    }

    @Transactional
    public void delete(Long id){
        userMapper.deleteById(id);
    }

    @Test
    public void select(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","chenlin");
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.allEq(map);
        List<User> list= userMapper.selectList(wrapper);
        System.out.println(list.get(0));
    }

}

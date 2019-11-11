package com.wondersgrop.model.web;

import com.wondersgrop.model.entity.SysUser;
import com.wondersgrop.model.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author chenlin
 * @create 2019-06-24 13:31
 * @description: TODO
 * @version：1.0
 **/
@Controller
public class UserContorller {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/get")
    @ResponseBody
    public SysUser getUser() {

        return userDao.query(10L);
    }

    //清除缓存
    @Autowired
    private CacheManager cacheManager;

    @ResponseBody
    @RequestMapping("/clear")
    public String clear() {
        cacheManager.getCache("user").clear();
        return "success";
    }
}

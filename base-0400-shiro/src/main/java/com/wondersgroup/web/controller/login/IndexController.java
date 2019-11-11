package com.wondersgroup.web.controller.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author chenlin
 * @create 2019-06-13 22:21
 * @description: TODO
 * @version：1.0
 **/
@Controller
public class IndexController {

    @RequiresPermissions(value = "sys:view")
    @GetMapping(value = "/index")
    public String index(HttpSession session) {
        System.out.println(session.getId());
        System.out.println(SecurityUtils.getSubject().getSession().getId());
        /*
        Subject subject = SecurityUtils.getSubject();

        subject.checkPermission("sys:user:view111");
        */
        return "index";
    }

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "welcome";
    }
}

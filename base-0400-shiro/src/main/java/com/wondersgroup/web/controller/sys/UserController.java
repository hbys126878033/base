package com.wondersgroup.web.controller.sys;

import com.github.pagehelper.Page;
import com.wondersgroup.core.web.view.ResultPageView;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenlin
 * @create 2019-06-17 12:30
 * @description: TODO
 * @version：1.0
 **/

@Controller
@RequestMapping(value = "/sys/user")
public class UserController {

    private static final String PREFIX = "/sys/user/";

    @GetMapping()
    public String user() {

        return PREFIX + "user";
    }

    @PostMapping(value = "/list")
    public ResultPageView list() {
        return new ResultPageView(new Page<>());
    }

    @RequiresPermissions("sys:user:delete")
    @GetMapping(value = "/add")
    public String add() {
        return PREFIX + "user_add";
    }
}

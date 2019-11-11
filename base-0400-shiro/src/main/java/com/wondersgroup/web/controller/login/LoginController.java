package com.wondersgroup.web.controller.login;

import com.wondersgroup.core.web.view.ResultView;
import com.wondersgroup.model.entity.SysUser;
import com.wondersgroup.model.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenlin
 * @create 2019-06-13 22:20
 * @description: 系统登录控制器
 * @version：1.0
 **/
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "/")
    public String defaultPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResultView login(String username, String password) {

        logger.info("login Controller ,username={},password={}", username, password);

        try {
            if (!StringUtils.hasText(username)) {
                return new ResultView(false, "用户名密码不能为空");
            }
            if (!StringUtils.hasText(password)) {
                return new ResultView(false, "用户名密码不能为空");
            }

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return new ResultView();
        } catch (AuthenticationException e) {
            e.printStackTrace();

            return new ResultView(false, e.getMessage());

        }
    }


    @GetMapping(value = "logout")
    public String logout() {
        return "redirect:/";
    }

}

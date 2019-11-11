package com.wondersgroup.web.controller.exception;

import com.wondersgroup.core.web.view.ResultView;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.PermissionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenlin
 * @create 2019-06-20 17:12
 * @description: 全局异常处理，暂时没有做AJAX请求的异常处理
 * @version：1.0
 **/
@RestControllerAdvice
public class CustomException {

    private static final Logger logger = LoggerFactory.getLogger(CustomException.class);

    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
     */
    @ExceptionHandler({AuthorizationException.class, UnauthorizedException.class})
    public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
       /* log.error(e.getMessage(), e);
        if (ServletUtils.isAjaxRequest(request))
        {
            return AjaxResult.error(PermissionUtils.getMsg(e.getMessage()));
        }
        else
        {*/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/unauth");
        return modelAndView;
        /* }*/
    }
}

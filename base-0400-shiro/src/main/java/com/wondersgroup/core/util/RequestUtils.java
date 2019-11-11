package com.wondersgroup.core.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author chenlin
 * @create 2019-06-21 9:03
 * @description: HttpServletRequest帮助类
 * @version：1.0
 **/
public class RequestUtils {

    /**
     * 方法作用：获取name参数的值
     *
     * @param name 参数名称
     * @return: java.lang.String 参数值的类型
     * @createDate: 2019/6/21 15:50
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 15:50
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String getParameter(String name) {

        return getRequest().getParameter(name);
    }

    /**
     * 方法作用：获取name的参数值，如果为空，返回指定的默认值
     *
     * @param name
     * @param defaultValue
     * @return: java.lang.String
     * @createDate: 2019/6/21 15:51
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 15:51
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String getParameter(String name, String defaultValue) {

        return ConvertUtils.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 方法作用：获取参数name的参数值，并且值是Integer类型的
     *
     * @param name
     * @return: java.lang.Integer
     * @createDate: 2019/6/21 15:52
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 15:52
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static Integer getParameterToInt(String name) {

        return ConvertUtils.toInt(getRequest().getParameter(name));
    }

    /**
     * 方法作用：获取参数名称是name的，参数值是Interger类型的，并且提供一个默认值
     *
     * @param name
     * @param defaultValue
     * @return: java.lang.Integer
     * @createDate: 2019/6/21 15:53
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 15:53
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static Integer getParameterToInt(String name, Integer defaultValue) {

        return ConvertUtils.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 方法作用：获取HttpRequest对象
     *
     * @param
     * @return: javax.servlet.http.HttpServletRequest
     * @createDate: 2019/6/21 15:54
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 15:54
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static HttpServletRequest getRequest() {

        return getRequestAttributes().getRequest();
    }

    /**
     * 方法作用：获取HttpResponse对象
     *
     * @param
     * @return: javax.servlet.http.HttpServletResponse
     * @createDate: 2019/6/21 15:54
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 15:54
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static HttpServletResponse getResponse() {

        return getRequestAttributes().getResponse();
    }

    /**
     * 方法作用：获取HttpSession对象
     *
     * @param
     * @return: javax.servlet.http.HttpSession
     * @createDate: 2019/6/21 15:55
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 15:55
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static HttpSession getSession() {

        return getRequest().getSession();
    }

    /**
     * 方法作用：获取调用方法时的所有属性，
     * 在DispatcherServlet的父类
     * {@link FrameworkServlet#processRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     * 中，会设置此属性
     *
     * @param
     * @return: org.springframework.web.context.request.ServletRequestAttributes
     * @createDate: 2019/6/21 15:48
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 15:48
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static ServletRequestAttributes getRequestAttributes() {

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 方法作用：将字符串渲染到客户端
     *
     * @param response
     * @param string
     * @return: java.lang.String
     * @createDate: 2019/6/21 15:47
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 15:47
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String renderString(HttpServletResponse response, String string) {

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法作用：根据HttpServletRequest对象，获取客户端的真实IP。
     *
     * @param request
     * @return: java.lang.String
     * @createDate: 2019/6/21 9:08
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:08
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String getIpAddr(HttpServletRequest request) {

        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 方法作用：判断HttpRequest是否是AJAX请求
     *
     * @param request httpRequest对象
     * @return: boolean
     * @createDate: 2019/6/21 10:17
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 10:17
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml")) {
            return true;
        }
        return false;
    }

}

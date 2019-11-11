package com.wondersgroup.shiro.session;

import com.wondersgroup.core.util.RequestUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SimpleSessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenlin
 * @create 2019-06-21 15:39
 * @description: TODO
 * @version：1.0
 **/
public class CustomSessionFactory extends SimpleSessionFactory {
    @Override
    public Session createSession(SessionContext initData) {
        CustomSession session = new CustomSession();
        if (initData != null && initData instanceof WebSessionContext) {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (request != null) {
                UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader(HttpHeaders.USER_AGENT));
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                session.setHost(RequestUtils.getIpAddr(request));
                session.setBrowser(browser);
                session.setOs(os);
                System.out.println("browser:" + browser + ",os=" + os);
            }
        }
        return session;
    }
}

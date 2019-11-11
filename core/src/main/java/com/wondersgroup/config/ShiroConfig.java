package com.wondersgroup.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.wondersgroup.framework.commom.util.SpringUtils;
import com.wondersgroup.framework.commom.util.StringUtils;
import com.wondersgroup.framework.shiro.realm.UserRealm;
import com.wondersgroup.framework.shiro.session.OnlineSessionDAO;
import com.wondersgroup.framework.shiro.session.OnlineSessionFactory;
import com.wondersgroup.framework.shiro.web.filter.*;
import com.wondersgroup.framework.shiro.web.session.OnlineWebSessionManager;
import com.wondersgroup.framework.shiro.web.session.SpringSessionValidationScheduler;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 权限配置加载
 *
 * @author chenlin
 */

public class ShiroConfig {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    // Session超时时间，单位为毫秒（默认30分钟）
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    // 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
    @Value("${shiro.session.validationInterval}")
    private int validationInterval;

    // 同一个用户最大会话数
    @Value("${shiro.session.maxSession}")
    private int maxSession;

    // 踢出之前登录的/之后登录的用户，默认踢出之前登录的用户
    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    // 验证码开关
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    // 验证码类型
    @Value("${shiro.user.captchaType}")
    private String captchaType;

    // 设置Cookie的域名
    @Value("${shiro.cookie.domain}")
    private String domain;

    // 设置cookie的有效访问路径
    @Value("${shiro.cookie.path}")
    private String path;

    // 设置HttpOnly属性
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    // 设置Cookie的过期时间，秒为单位
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    // 登录地址
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    // 权限认证失败地址
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    /**
     * 方法作用：缓存管理器 使用Ehcache实现
     *
     * @param
     * @return: org.apache.shiro.cache.ehcache.EhCacheManager
     * @createDate: 2019/7/3 15:18
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:18
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public EhCacheManager getEhCacheManager() {

        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getCacheManager("base-core");
        EhCacheManager em = new EhCacheManager();
        if (StringUtils.isNull(cacheManager)) {
            em.setCacheManager(new net.sf.ehcache.CacheManager(getCacheManagerConfigFileInputStream()));
            return em;
        } else {
            em.setCacheManager(cacheManager);
            return em;
        }
    }

    /**
     * 方法作用：返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
     *
     * @param
     * @return: java.io.InputStream
     * @createDate: 2019/7/3 15:21
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:21
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    protected InputStream getCacheManagerConfigFileInputStream() {

        String configFile = "classpath:ehcache/ehcache-shiro.xml";
        InputStream inputStream = null;
        try {
            inputStream = ResourceUtils.getInputStreamForPath(configFile);
            byte[] b = IOUtils.toByteArray(inputStream);
            InputStream in = new ByteArrayInputStream(b);
            return in;
        } catch (IOException e) {
            throw new ConfigurationException(
                    "Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 方法作用：自定义Realm
     *
     * @param cacheManager
     * @return: com.wondersgroup.framework.shiro.realm.UserRealm
     * @createDate: 2019/7/3 15:21
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:21
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public UserRealm userRealm(EhCacheManager cacheManager) {

        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }

    /**
     * 方法作用：自定义sessionDAO会话
     *
     * @param
     * @return: com.wondersgroup.framework.shiro.session.OnlineSessionDAO
     * @createDate: 2019/7/3 15:22
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:22
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public OnlineSessionDAO sessionDAO() {

        OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
        return sessionDAO;
    }

    /**
     * 方法作用：自定义sessionFactory会话
     *
     * @param
     * @return: com.wondersgroup.framework.shiro.session.OnlineSessionFactory
     * @createDate: 2019/7/3 15:23
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:23
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public OnlineSessionFactory sessionFactory() {

        OnlineSessionFactory sessionFactory = new OnlineSessionFactory();
        return sessionFactory;
    }

    /**
     * 方法作用： 会话管理器
     *
     * @param
     * @return: com.wondersgroup.framework.shiro.web.session.OnlineWebSessionManager
     * @createDate: 2019/7/3 15:23
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:23
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public OnlineWebSessionManager sessionManager() {

        OnlineWebSessionManager manager = new OnlineWebSessionManager();
        /*加入缓存管理器 */
        manager.setCacheManager(getEhCacheManager());
        /*删除过期的session */
        manager.setDeleteInvalidSessions(true);
        /*设置全局session超时时间 */
        manager.setGlobalSessionTimeout(expireTime * 60 * 1000);
        /* 去掉 JSESSIONID */
        manager.setSessionIdUrlRewritingEnabled(false);
        /* 定义要使用的无效的Session定时调度器 */
        manager.setSessionValidationScheduler(SpringUtils.getBean(SpringSessionValidationScheduler.class));
        /* 是否定时检查session */
        manager.setSessionValidationSchedulerEnabled(true);
        /* 自定义SessionDao */
        manager.setSessionDAO(sessionDAO());
        /* 自定义sessionFactory */
        manager.setSessionFactory(sessionFactory());
        return manager;
    }

    /**
     * 方法作用：安全管理器
     *
     * @param userRealm
     * @param springSessionValidationScheduler
     * @return: org.apache.shiro.mgt.SecurityManager
     * @createDate: 2019/7/3 15:25
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:25
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public SecurityManager securityManager(UserRealm userRealm, SpringSessionValidationScheduler springSessionValidationScheduler) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        /*设置realm.*/
        securityManager.setRealm(userRealm);
        /* 记住我*/
        securityManager.setRememberMeManager(rememberMeManager());
        /*注入缓存管理器;*/
        securityManager.setCacheManager(getEhCacheManager());
        /* session管理器*/
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 方法作用：退出过滤器
     *
     * @param
     * @return: com.wondersgroup.framework.shiro.web.filter.LogoutFilter
     * @createDate: 2019/7/3 15:25
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:25
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public LogoutFilter logoutFilter() {

        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setCacheManager(getEhCacheManager());
        logoutFilter.setLoginUrl(loginUrl);
        return logoutFilter;
    }

    /**
     * 方法作用：Shiro过滤器配置
     *
     * @param securityManager
     * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @createDate: 2019/7/3 15:26
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:26
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /*Shiro的核心安全接口,这个属性是必须的*/
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /* 身份认证失败，则跳转到登录页面的配置*/
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        /* 权限认证失败，则跳转到指定页面*/
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        /* Shiro连接约束配置，即过滤链的定义*/
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /* 对静态资源设置匿名访问*/
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/ruoyi.png**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/ajax/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/ruoyi/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/captcha/captchaImage**", "anon");
        /* 退出 logout地址，shiro去清除session*/
        filterChainDefinitionMap.put("/logout", "logout");
        /* 不需要拦截的访问*/
        filterChainDefinitionMap.put("/login", "anon,captchaValidate");
        /* 系统权限列表*/
        // filterChainDefinitionMap.putAll(SpringUtils.getBean(IMenuService.class).selectPermsAll());

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("onlineSession", onlineSessionFilter());
        filters.put("syncOnlineSession", syncOnlineSessionFilter());
        filters.put("captchaValidate", captchaValidateFilter());
        filters.put("kickout", kickoutSessionFilter());
        /*注销成功，则跳转到指定页面*/
        filters.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        /*所有请求需要认证*/
        filterChainDefinitionMap.put("/**", "user,kickout,onlineSession,syncOnlineSession");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 方法作用：自定义在线用户处理过滤器
     *
     * @param
     * @return: com.wondersgroup.framework.shiro.web.filter.OnlineSessionFilter
     * @createDate: 2019/7/3 15:27
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:27
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public OnlineSessionFilter onlineSessionFilter() {

        OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
        onlineSessionFilter.setLoginUrl(loginUrl);
        return onlineSessionFilter;
    }

    /**
     * 方法作用：自定义在线用户同步过滤器
     *
     * @param
     * @return: com.wondersgroup.framework.shiro.web.filter.SyncOnlineSessionFilter
     * @createDate: 2019/7/3 15:27
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:27
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public SyncOnlineSessionFilter syncOnlineSessionFilter() {

        SyncOnlineSessionFilter syncOnlineSessionFilter = new SyncOnlineSessionFilter();
        return syncOnlineSessionFilter;
    }


    /**
     * 方法作用：自定义验证码过滤器
     *
     * @param
     * @return: com.wondersgroup.framework.shiro.web.filter.CaptchaValidateFilter
     * @createDate: 2019/7/3 15:27
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:27
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public CaptchaValidateFilter captchaValidateFilter() {

        CaptchaValidateFilter captchaValidateFilter = new CaptchaValidateFilter();
        captchaValidateFilter.setCaptchaEnabled(captchaEnabled);
        captchaValidateFilter.setCaptchaType(captchaType);
        return captchaValidateFilter;
    }

    /**
     * 方法作用：cookie 属性设置
     *
     * @param
     * @return: org.apache.shiro.web.servlet.SimpleCookie
     * @createDate: 2019/7/3 15:28
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:28
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public SimpleCookie rememberMeCookie() {

        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);
        return cookie;
    }

    /**
     * 方法作用：记住我
     *
     * @param
     * @return: org.apache.shiro.web.mgt.CookieRememberMeManager
     * @createDate: 2019/7/3 15:29
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:29
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public CookieRememberMeManager rememberMeManager() {

        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        return cookieRememberMeManager;
    }

    /**
     * 方法作用：     * 方法作用同一个用户多设备登录限制：
     *
     * @param
     * @return: com.wondersgroup.framework.shiro.web.filter.KickoutSessionFilter
     * @createDate: 2019/7/3 15:28
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:28
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public KickoutSessionFilter kickoutSessionFilter() {

        KickoutSessionFilter kickoutSessionFilter = new KickoutSessionFilter();
        kickoutSessionFilter.setCacheManager(getEhCacheManager());
        kickoutSessionFilter.setSessionManager(sessionManager());
        /*同一个用户最大的会话数，默认-1无限制；比如2的意思是同一个用户允许最多同时两个人登录*/
        kickoutSessionFilter.setMaxSession(maxSession);
        /* 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序*/
        kickoutSessionFilter.setKickoutAfter(kickoutAfter);
        /* 被踢出后重定向到的地址；*/
        kickoutSessionFilter.setKickoutUrl("/login?kickout=1");
        return kickoutSessionFilter;
    }


    /**
     * 方法作用：thymeleaf模板引擎和shiro框架的整合
     *
     * @param
     * @return: at.pollux.thymeleaf.shiro.dialect.ShiroDialect
     * @createDate: 2019/7/3 15:28
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:28
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public ShiroDialect shiroDialect() {

        return new ShiroDialect();
    }

    /**
     * 方法作用：开启Shiro注解通知器
     *
     * @param securityManager
     * @return: org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @createDate: 2019/7/3 15:28
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 15:28
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

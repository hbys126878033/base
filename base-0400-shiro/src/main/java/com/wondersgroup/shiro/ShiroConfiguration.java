package com.wondersgroup.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.wondersgroup.shiro.listener.CustomSessionListener;
import com.wondersgroup.shiro.realm.LoginRealm;
import com.wondersgroup.shiro.session.CustomSessionFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.cache.RedisCache;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author chenlin
 * @create 2019-06-19 21:49
 * @description: Shiro相关的配置中心
 * @version：1.0
 **/
@Configuration
public class ShiroConfiguration {


    /**
     * 方法作用：创建域，用于用户身份认证和授权 具体见{@link LoginRealm}
     *
     * @param
     * @return: com.wondersgroup.shiro.LoginRealm
     * @createDate: 2019/6/19 21:53
     * @createAuthor: chenlin
     * @updateDate: 2019/6/19 21:53
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public LoginRealm loginRealm() {
        return new LoginRealm();
    }


    @Bean
    public CustomSessionFactory customSessionFactory() {
        return new CustomSessionFactory();
    }


    @Bean
    public CustomSessionListener customSessionListener() {
        return new CustomSessionListener();
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        /*删除过期的session*/
        sessionManager.setDeleteInvalidSessions(true);
        /*设置全局session超时时间*/
        //sessionManager.setGlobalSessionTimeout(expireTime * 60 * 1000);

        // 去掉 JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        /*加入会话的监听器*/
        sessionManager.setSessionListeners(Arrays.asList(customSessionListener()));
        /*使用自定义的创建会话的工厂，shiro默认使用SimpleSessionFactory来创建SimpleSession*/
        sessionManager.setSessionFactory(customSessionFactory());

        /**
         * 还可以加入的特性 ：
         *         1) 会话验证调度器，验证会话是否有效
         *         2) 会话存储，可以把会话信息存储到数据库中
         *         3) 设置会话的超时时间
         *         4) 缓存机器，会话信息可以缓存
         *
         * */
        return sessionManager;
    }

    /**
     * 方法作用：实例化安全管理器，shiro的核心，所有有关安全相关的操作都需要与SecurityManager交互，并且管理着Subject（主体，可以看做用户）
     *
     * @param
     * @return: org.apache.shiro.mgt.SecurityManager
     * @createDate: 2019/6/19 21:54
     * @createAuthor: chenlin
     * @updateDate: 2019/6/19 21:54
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public SecurityManager securityManager() {
        /*web项目使用web相关的安全管理器*/
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        /*加入自己定制的认证授权相关的Realm*/
        securityManager.setRealm(loginRealm());

        /*//使用自定义的SessionManager，也可以使用默认的*/
        securityManager.setSessionManager(defaultWebSessionManager());

        /**
         * 认证策略：实例化ModularRealmAuthorizer时，指定了至少一个认证通过的策略
         *
         *
         * SessionManager:管理主体与应用之间交互的数据，会话管理器
         *  SessionManager默认使用的是ServletContainerSessionManager，
         *      特点是始终代理的是servlet容器进行会话，即HttpSession的代理对象
         *
         *  DefaultWebSessionManager:特点是本地会话管理器实现
         */

        /**
         * 其他特性：
         *     1) 记住我功能
         *     2) 缓存管理器功能，可以使用EhCacheManage或redis
         */
        return securityManager;
    }

    /**
     * 方法作用：创建一个shiro的过滤器，原理是请求在进入控制层之前对请求进行安全认证，
     * 所以需要配置认证的策略
     *
     * @param
     * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @createDate: 2019/6/19 22:48
     * @createAuthor: chenlin
     * @updateDate: 2019/6/19 22:48
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        /**
         * ShiroFilterFactoryBean:是一个工厂Bean，返回的对象是ShiroFilter的实例，
         *                        在创建实例中会把Shiro提供的Filter都初始化进去，并且需要设置loginUrl，successUrl和unauthorizedUrl
         *                        Shiro的Filter列表 见 {@link DefaultFilter}
         *
         *                        自己实现的Filter，则通过ShiroFilterFactoryBean中的postProcessBeforeInitialization方法初始化三个属性
         *
         */
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*配置不会被拦截的链接，顺序判断*/
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/ajax/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/file/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/ruoyi/**", "anon");
        filterChainDefinitionMap.put("/login**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        /**
         * authc:表示FormAuthenticationFilter的拦截器，
         *
         * authc 验证不通过时，会创建session
         */
        filterChainDefinitionMap.put("/**", "authc");


        //authc:所有url必须通过认证才能访问，anon:所有url都可以匿名访问
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilter.setLoginUrl("/");
        return shiroFilter;

    }

    /**
     * Shiro生命周期处理器 * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 方法作用：thymeleaf模板引擎和shiro框架的整合,使shiro在HTML中配置属性使用
     * 例如：<a class="btn btn-success" onclick="$.operate.addTab()" shiro:hasPermission="sys:user:add">添加</a>
     *
     * @param
     * @return: at.pollux.thymeleaf.shiro.dialect.ShiroDialect
     * @createDate: 2019/6/21 16:46
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 16:46
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Bean
    public ShiroDialect shiroDialect() {

        return new ShiroDialect();
    }

}

package com.wondersgroup.shiro.realm;

import com.wondersgroup.model.entity.SysUser;
import com.wondersgroup.model.service.SysMenuService;
import com.wondersgroup.model.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenlin
 * @create 2019-06-19 21:29
 * @description: 权限认证器，继承AuthorizingRealm，实现认证和授权的方法，
 * SecurityManager通过reaml获取身份，可以把reaml看做是安全数据源,
 * 可以是多重实现方式，比如文件，内存，或者JDBC
 * <p>
 * 注意：暂时密码没有加密
 * @version：1.0
 **/
public class LoginRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(LoginRealm.class);


    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 授权方法:
     * 1.需要把用户所拥有的的角色和权限赋给授权器
     * 2.shiro的授权方式：
     * 1)编码方式:SecurityUtils.getSubject()的hasRole()方法,checkPermission()方法等
     * 2)注解方式：@RequiresRoles ,@RequiresPermissions 等注解
     * 3)JSP标签方式：<shiro:hasRole name="admin"></shiro:hasRole><!— 有权限 —></shiro:hasRole> shiro:hasPermission等
     * 添加thymeleaf-extras-shiro的jar包，也可以像hymeleaf一样使用shiro，
     * HTML配置：<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
     * 3.当使用上面三种方式中的一种方式授权时，shiro会执行doGetAuthorizationInfo来授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("----用户授权----");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println(SecurityUtils.getSubject().getPrincipal());

        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        System.out.println(user);
        List<String> permissions = sysMenuService.queryMenuPermissionByUserId(user.getId());
        if (permissions != null && !permissions.isEmpty()) {
            info.addStringPermissions(permissions);
        }
        return info;
    }


    /**
     * 认证方法:
     * 登录方法通过SecurityUtils.getSubject().login(token)来调用此方法,token是需要在登录时创建的对象
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("----用户权限认证----");
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String username = t.getUsername();
        String password = new String(t.getPassword());
        SysUser user = sysUserService.queryUserByLoginName(username);
        if (user == null) {
            throw new AuthenticationException("用户名密码不匹配");
        }
        if (password.equals(user.getPassword())) {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
            return info;
        } else {
            throw new AuthenticationException("用户名密码不匹配");
        }

    }


    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}

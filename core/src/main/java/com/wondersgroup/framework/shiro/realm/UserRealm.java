package com.wondersgroup.framework.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author chenlin
 * @create 2019-07-03 13:37
 * @description: 认证授权的域
 * @version：1.0
 **/
public class UserRealm extends AuthorizingRealm {

    /**
     * 方法作用：授权方法，需要把用户所拥有的的角色和权限赋给授权器
     * <p>
     * 执行该方法的时机：shiro的授权方式：
     * 1)编码方式:SecurityUtils.getSubject()的hasRole()方法,checkPermission()方法等
     * 2)注解方式：@RequiresRoles ,@RequiresPermissions 等注解
     * 3)JSP标签方式：<shiro:hasRole name="admin"></shiro:hasRole><!— 有权限 —></shiro:hasRole> shiro:hasPermission等
     * 添加thymeleaf-extras-shiro的jar包，也可以像hymeleaf一样使用shiro，
     * HTML配置：<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
     *
     * @param principals
     * @return: org.apache.shiro.authz.AuthorizationInfo
     * @createDate: 2019/7/3 13:39
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 13:39
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

    /**
     * 方法作用：认证方法
     * 通过SecurityUtils.getSubject().login(token)来调用此方法,token是需要在登录时创建的对象
     *
     * @param token
     * @return: org.apache.shiro.authc.AuthenticationInfo
     * @createDate: 2019/7/3 13:39
     * @createAuthor: chenlin
     * @updateDate: 2019/7/3 13:39
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        return null;
    }


    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}

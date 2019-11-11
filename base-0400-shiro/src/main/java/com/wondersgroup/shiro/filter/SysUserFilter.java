package com.wondersgroup.shiro.filter;

import com.wondersgroup.core.constant.SysConstant;
import com.wondersgroup.model.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class SysUserFilter extends PathMatchingFilter {

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(SysConstant.CURRENT_USER, user);
        return true;
    }
}

package com.wondersgroup.model.service;

import com.wondersgroup.model.entity.SysMenu;

import java.util.List;

/**
 * @author chenlin
 * @create 2019-06-18 16:54
 * @description: TODO
 * @version：1.0
 **/
public interface SysMenuService {
    /**
     * 方法作用：
     *
     * @param sysMenu
     * @return: java.lang.String
     * @createDate: 2019/6/18 16:55
     * @createAuthor: chenlin
     * @updateDate: 2019/6/18 16:55
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public String addMenu(SysMenu sysMenu);

    /**
     * 方法作用：
     *
     * @param id
     * @return: java.util.List<java.lang.String>
     * @createDate: 2019/6/18 16:56
     * @createAuthor: chenlin
     * @updateDate: 2019/6/18 16:56
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public List<String> queryMenuPermissionByUserId(Long id);
}

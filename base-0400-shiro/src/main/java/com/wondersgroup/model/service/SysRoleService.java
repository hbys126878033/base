package com.wondersgroup.model.service;

import java.util.List;

/**
 * @author chenlin
 * @create 2019-06-18 16:56
 * @description: TODO
 * @version：1.0
 **/
public interface SysRoleService {


    /**
     * 方法作用：查询用户所有的角色信息
     *
     * @param id
     * @return: java.util.List<java.lang.String>
     * @createDate: 2019/6/18 16:56
     * @createAuthor: chenlin
     * @updateDate: 2019/6/18 16:56
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public List<String> queryRoleByUserId(Long id);
}

package com.wondersgroup.model.service;

import com.wondersgroup.model.entity.SysUser;
import com.wondersgroup.model.mapper.SysUserMapper;

/**
 * @author chenlin
 * @create 2019-06-17 21:59
 * @description: TODO
 * @version：1.0
 **/
public interface SysUserService {

    /**
     * 方法作用：
     *
     * @param user
     * @return: java.lang.String
     * @createDate: 2019/6/17 22:03
     * @createAuthor: chenlin
     * @updateDate: 2019/6/17 22:03
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public String addUser(SysUser user);


    /**
     * 方法作用：
     *
     * @param id
     * @return: com.wondersgroup.model.entity.SysUser
     * @createDate: 2019/6/17 22:03
     * @createAuthor: chenlin
     * @updateDate: 2019/6/17 22:03
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public SysUser loadUser(Long id);

    /**
     * 方法作用：
     *
     * @param user
     * @return: java.lang.String
     * @createDate: 2019/6/17 22:04
     * @createAuthor: chenlin
     * @updateDate: 2019/6/17 22:04
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public String updateUser(SysUser user);

    /**
     * 方法作用：
     *
     * @param ids
     * @return: java.lang.String
     * @createDate: 2019/6/17 22:04
     * @createAuthor: chenlin
     * @updateDate: 2019/6/17 22:04
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public String delete(Long[] ids);

    /**
     * 方法作用：根据用户名查询用户信息
     *
     * @param loginName
     * @return: com.wondersgroup.model.entity.SysUser
     * @createDate: 2019/6/18 17:11
     * @createAuthor: chenlin
     * @updateDate: 2019/6/18 17:11
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public SysUser queryUserByLoginName(String loginName);

}

package com.wondersgroup.model.service.impl;

import com.wondersgroup.core.constant.ResultConstant;
import com.wondersgroup.core.exception.CustomException;
import com.wondersgroup.model.entity.SysUser;
import com.wondersgroup.model.mapper.SysUserMapper;
import com.wondersgroup.model.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;

/**
 * @author chenlin
 * @create 2019-06-17 22:05
 * @description: 用户Services
 * @version：1.0
 **/

@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);


    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional
    public String addUser(SysUser user) {

        sysUserMapper.insertSelective(user);

        return ResultConstant.OK;

    }

    @Override
    public SysUser loadUser(Long id) {
        if (StringUtils.isEmpty(id)) {
            throw new CustomException("ID不能为空");
        }
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public String updateUser(SysUser user) {
        if (StringUtils.isEmpty(user)) {
            throw new CustomException("参数传递错误");
        }
        if (StringUtils.isEmpty(user.getId())) {
            throw new CustomException("id不能为空");
        }
        SysUser bo = sysUserMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(bo)) {
            throw new CustomException("没有此对象");
        }

        return null;
    }

    @Override
    public String delete(Long[] ids) {
        return null;
    }

    @Override
    public SysUser queryUserByLoginName(String loginName) {
        return sysUserMapper.selectByLoginName(loginName);
    }
}

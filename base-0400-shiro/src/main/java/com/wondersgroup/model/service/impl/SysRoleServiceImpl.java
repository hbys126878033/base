package com.wondersgroup.model.service.impl;

import com.wondersgroup.model.mapper.SysRoleMapper;
import com.wondersgroup.model.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenlin
 * @create 2019-06-18 16:57
 * @description: TODO
 * @version：1.0
 **/
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);


    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> queryRoleByUserId(Long id) {

        return null;
    }
}

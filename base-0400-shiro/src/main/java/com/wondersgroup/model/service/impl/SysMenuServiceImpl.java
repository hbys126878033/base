package com.wondersgroup.model.service.impl;

import com.wondersgroup.model.entity.SysMenu;
import com.wondersgroup.model.mapper.SysMenuMapper;
import com.wondersgroup.model.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenlin
 * @create 2019-06-18 17:05
 * @description: TODO
 * @version：1.0
 **/
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    private static final Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public String addMenu(SysMenu sysMenu) {
        return null;
    }

    @Override
    public List<String> queryMenuPermissionByUserId(Long id) {
        return sysMenuMapper.queryPermissionByUserId(id);
    }
}

package com.wondersgroup.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wondersgroup.entity.User;

import java.util.List;

/**
 * @author chenlin
 * @create 2019-11-11 9:41
 * @description: 集成mybatis-plus中的BaseMapper，并且配置mappertoSapn
 * @version：1.0
 **/
public interface UserMapper extends BaseMapper<User> {

    public List<User> queryForPage();
}

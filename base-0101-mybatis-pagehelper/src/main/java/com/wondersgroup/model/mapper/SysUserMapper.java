package com.wondersgroup.model.mapper;

import com.github.pagehelper.Page;
import com.wondersgroup.model.entity.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysUserMapper {


    @Select({"select * from sys_user"})
    @ResultMap("com.wondersgroup.model.mapper.SysUserMapper.BaseResultMap")
    Page<SysUser> query();

    @Delete({
            "delete from sys_user",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_user (ID, LOGIN_NAME, ",
            "PASSWORD, NAME, ",
            "PHONE, STATUS, EMAIL, ",
            "USER_ORDER, INTERNAL, ",
            "CREATE_TIME, CREATE_ID, ",
            "UPDATE_TIME, UPDATE_ID)",
            "values (#{id,jdbcType=BIGINT}, #{loginName,jdbcType=VARCHAR}, ",
            "#{password,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
            "#{phone,jdbcType=CHAR}, #{status,jdbcType=CHAR}, #{email,jdbcType=VARCHAR}, ",
            "#{userOrder,jdbcType=INTEGER}, #{internal,jdbcType=CHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{createId,jdbcType=BIGINT}, ",
            "#{updateTime,jdbcType=TIMESTAMP}, #{updateId,jdbcType=BIGINT})"
    })
    int insert(SysUser record);

    int insertSelective(SysUser record);

    @Select({
            "select",
            "ID, LOGIN_NAME, PASSWORD, NAME, PHONE, STATUS, EMAIL, USER_ORDER, INTERNAL, ",
            "CREATE_TIME, CREATE_ID, UPDATE_TIME, UPDATE_ID",
            "from sys_user",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysUserMapper.BaseResultMap")
    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    @Update({
            "update sys_user",
            "set LOGIN_NAME = #{loginName,jdbcType=VARCHAR},",
            "PASSWORD = #{password,jdbcType=VARCHAR},",
            "NAME = #{name,jdbcType=VARCHAR},",
            "PHONE = #{phone,jdbcType=CHAR},",
            "STATUS = #{status,jdbcType=CHAR},",
            "EMAIL = #{email,jdbcType=VARCHAR},",
            "USER_ORDER = #{userOrder,jdbcType=INTEGER},",
            "INTERNAL = #{internal,jdbcType=CHAR},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
            "CREATE_ID = #{createId,jdbcType=BIGINT},",
            "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
            "UPDATE_ID = #{updateId,jdbcType=BIGINT}",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUser record);
}
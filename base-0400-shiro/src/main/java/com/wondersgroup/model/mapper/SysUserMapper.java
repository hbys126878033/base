package com.wondersgroup.model.mapper;

import com.wondersgroup.model.entity.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysUserMapper {
    @Delete({
            "delete from sys_user",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_user (id, login_name, ",
            "password, name, ",
            "phone, status, email, ",
            "user_order, internal, ",
            "create_time, create_by, ",
            "update_time, update_by)",
            "values (#{id,jdbcType=BIGINT}, #{loginName,jdbcType=VARCHAR}, ",
            "#{password,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
            "#{phone,jdbcType=CHAR}, #{status,jdbcType=CHAR}, #{email,jdbcType=VARCHAR}, ",
            "#{userOrder,jdbcType=INTEGER}, #{internal,jdbcType=CHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{createBy,jdbcType=BIGINT}, ",
            "#{updateTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=BIGINT})"
    })
    int insert(SysUser record);

    int insertSelective(SysUser record);

    @Select({
            "select",
            "id, login_name, password, name, phone, status, email, user_order, internal, ",
            "create_time, create_by, update_time, update_by",
            "from sys_user",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysUserMapper.BaseResultMap")
    SysUser selectByPrimaryKey(Long id);

    @Select({
            "select",
            "id, login_name, password, name, phone, status, email, user_order, internal, ",
            "create_time, create_by, update_time, update_by",
            "from sys_user",
            "where login_name = #{loginName,jdbcType=VARCHAR}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysUserMapper.BaseResultMap")
    SysUser selectByLoginName(String loginName);

    int updateByPrimaryKeySelective(SysUser record);

    @Update({
            "update sys_user",
            "set login_name = #{loginName,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=CHAR},",
            "status = #{status,jdbcType=CHAR},",
            "email = #{email,jdbcType=VARCHAR},",
            "user_order = #{userOrder,jdbcType=INTEGER},",
            "internal = #{internal,jdbcType=CHAR},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "create_by = #{createBy,jdbcType=BIGINT},",
            "update_time = #{updateTime,jdbcType=TIMESTAMP},",
            "update_by = #{updateBy,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUser record);
}
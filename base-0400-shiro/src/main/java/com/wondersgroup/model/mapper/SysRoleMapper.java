package com.wondersgroup.model.mapper;

import com.wondersgroup.model.entity.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysRoleMapper {
    @Delete({
            "delete from sys_role",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_role (id, name, ",
            "code, remark, is_removed, ",
            "create_time, create_by, ",
            "update_time, update_by)",
            "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
            "#{code,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{isRemoved,jdbcType=CHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{createBy,jdbcType=BIGINT}, ",
            "#{updateTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=BIGINT})"
    })
    int insert(SysRole record);

    int insertSelective(SysRole record);

    @Select({
            "select",
            "id, name, code, remark, is_removed, create_time, create_by, update_time, update_by",
            "from sys_role",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysRoleMapper.BaseResultMap")
    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    @Update({
            "update sys_role",
            "set name = #{name,jdbcType=VARCHAR},",
            "code = #{code,jdbcType=VARCHAR},",
            "remark = #{remark,jdbcType=VARCHAR},",
            "is_removed = #{isRemoved,jdbcType=CHAR},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "create_by = #{createBy,jdbcType=BIGINT},",
            "update_time = #{updateTime,jdbcType=TIMESTAMP},",
            "update_by = #{updateBy,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysRole record);

}
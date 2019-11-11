package com.wondersgroup.model.mapper;

import com.wondersgroup.model.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysRoleMapper {
    @Delete({
            "delete from sys_role",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_role (ID, NAME, ",
            "CODE, REMARK, IS_REMOVED, ",
            "CREATE_TIME, CREATE_ID, ",
            "UPDATE_TIME, UPDATE_ID)",
            "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
            "#{code,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{isRemoved,jdbcType=CHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{createId,jdbcType=BIGINT}, ",
            "#{updateTime,jdbcType=TIMESTAMP}, #{updateId,jdbcType=BIGINT})"
    })
    int insert(SysRole record);

    int insertSelective(SysRole record);

    @Select({
            "select",
            "ID, NAME, CODE, REMARK, IS_REMOVED, CREATE_TIME, CREATE_ID, UPDATE_TIME, UPDATE_ID",
            "from sys_role",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysRoleMapper.BaseResultMap")
    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    @Update({
            "update sys_role",
            "set NAME = #{name,jdbcType=VARCHAR},",
            "CODE = #{code,jdbcType=VARCHAR},",
            "REMARK = #{remark,jdbcType=VARCHAR},",
            "IS_REMOVED = #{isRemoved,jdbcType=CHAR},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
            "CREATE_ID = #{createId,jdbcType=BIGINT},",
            "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
            "UPDATE_ID = #{updateId,jdbcType=BIGINT}",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysRole record);
}
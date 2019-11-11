package com.wondersgroup.model.mapper;

import com.wondersgroup.model.entity.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysUserRoleMapper {
    @Delete({
            "delete from sys_user_role",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_user_role (ID, USER_ID, ",
            "ROLE_ID)",
            "values (#{id,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, ",
            "#{roleId,jdbcType=BIGINT})"
    })
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    @Select({
            "select",
            "ID, USER_ID, ROLE_ID",
            "from sys_user_role",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysUserRoleMapper.BaseResultMap")
    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    @Update({
            "update sys_user_role",
            "set USER_ID = #{userId,jdbcType=BIGINT},",
            "ROLE_ID = #{roleId,jdbcType=BIGINT}",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUserRole record);
}
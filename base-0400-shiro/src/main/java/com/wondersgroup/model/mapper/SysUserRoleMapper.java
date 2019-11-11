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
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_user_role (id, user_id, ",
            "role_id)",
            "values (#{id,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, ",
            "#{roleId,jdbcType=BIGINT})"
    })
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    @Select({
            "select",
            "id, user_id, role_id",
            "from sys_user_role",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysUserRoleMapper.BaseResultMap")
    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    @Update({
            "update sys_user_role",
            "set user_id = #{userId,jdbcType=BIGINT},",
            "role_id = #{roleId,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUserRole record);
}
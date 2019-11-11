package com.wondersgroup.model.mapper;

import com.wondersgroup.model.entity.SysGroupRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysGroupRoleMapper {
    @Delete({
            "delete from sys_group_role",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_group_role (ID, ROLE_ID, ",
            "GROUP_ID)",
            "values (#{id,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT}, ",
            "#{groupId,jdbcType=BIGINT})"
    })
    int insert(SysGroupRole record);

    int insertSelective(SysGroupRole record);

    @Select({
            "select",
            "ID, ROLE_ID, GROUP_ID",
            "from sys_group_role",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysGroupRoleMapper.BaseResultMap")
    SysGroupRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysGroupRole record);

    @Update({
            "update sys_group_role",
            "set ROLE_ID = #{roleId,jdbcType=BIGINT},",
            "GROUP_ID = #{groupId,jdbcType=BIGINT}",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysGroupRole record);
}
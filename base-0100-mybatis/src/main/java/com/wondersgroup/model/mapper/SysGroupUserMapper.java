package com.wondersgroup.model.mapper;

import com.wondersgroup.model.entity.SysGroupUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysGroupUserMapper {
    @Delete({
            "delete from sys_group_user",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_group_user (ID, GROUP_ID, ",
            "USER_ID)",
            "values (#{id,jdbcType=BIGINT}, #{groupId,jdbcType=BIGINT}, ",
            "#{userId,jdbcType=BIGINT})"
    })
    int insert(SysGroupUser record);

    int insertSelective(SysGroupUser record);

    @Select({
            "select",
            "ID, GROUP_ID, USER_ID",
            "from sys_group_user",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysGroupUserMapper.BaseResultMap")
    SysGroupUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysGroupUser record);

    @Update({
            "update sys_group_user",
            "set GROUP_ID = #{groupId,jdbcType=BIGINT},",
            "USER_ID = #{userId,jdbcType=BIGINT}",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysGroupUser record);
}
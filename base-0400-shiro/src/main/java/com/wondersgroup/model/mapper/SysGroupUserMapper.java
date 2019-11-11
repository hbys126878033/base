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
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_group_user (id, group_id, ",
            "user_id)",
            "values (#{id,jdbcType=BIGINT}, #{groupId,jdbcType=BIGINT}, ",
            "#{userId,jdbcType=BIGINT})"
    })
    int insert(SysGroupUser record);

    int insertSelective(SysGroupUser record);

    @Select({
            "select",
            "id, group_id, user_id",
            "from sys_group_user",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysGroupUserMapper.BaseResultMap")
    SysGroupUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysGroupUser record);

    @Update({
            "update sys_group_user",
            "set group_id = #{groupId,jdbcType=BIGINT},",
            "user_id = #{userId,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysGroupUser record);
}
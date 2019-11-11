package com.wondersgroup.model.mapper;

import com.wondersgroup.model.entity.SysGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysGroupMapper {
    @Delete({
            "delete from sys_group",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_group (ID, NAME, ",
            "STATUS, IS_REMOVED)",
            "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
            "#{status,jdbcType=CHAR}, #{isRemoved,jdbcType=CHAR})"
    })
    int insert(SysGroup record);

    int insertSelective(SysGroup record);

    @Select({
            "select",
            "ID, NAME, STATUS, IS_REMOVED",
            "from sys_group",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysGroupMapper.BaseResultMap")
    SysGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysGroup record);

    @Update({
            "update sys_group",
            "set NAME = #{name,jdbcType=VARCHAR},",
            "STATUS = #{status,jdbcType=CHAR},",
            "IS_REMOVED = #{isRemoved,jdbcType=CHAR}",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysGroup record);
}
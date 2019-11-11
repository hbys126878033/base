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
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_group (id, name, ",
            "status, is_removed)",
            "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
            "#{status,jdbcType=CHAR}, #{isRemoved,jdbcType=CHAR})"
    })
    int insert(SysGroup record);

    int insertSelective(SysGroup record);

    @Select({
            "select",
            "id, name, status, is_removed",
            "from sys_group",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysGroupMapper.BaseResultMap")
    SysGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysGroup record);

    @Update({
            "update sys_group",
            "set name = #{name,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=CHAR},",
            "is_removed = #{isRemoved,jdbcType=CHAR}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysGroup record);
}
package com.wondersgroup.model.mapper;

import com.wondersgroup.model.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysRoleMenuMapper {
    @Delete({
            "delete from sys_role_menu",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_role_menu (id, role_id, ",
            "menu_id)",
            "values (#{id,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT}, ",
            "#{menuId,jdbcType=BIGINT})"
    })
    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    @Select({
            "select",
            "id, role_id, menu_id",
            "from sys_role_menu",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysRoleMenuMapper.BaseResultMap")
    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    @Update({
            "update sys_role_menu",
            "set role_id = #{roleId,jdbcType=BIGINT},",
            "menu_id = #{menuId,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysRoleMenu record);
}
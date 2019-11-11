package com.wondersgroup.model.mapper;

import com.wondersgroup.model.entity.SysMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SysMenuMapper {
    @Delete({
            "delete from sys_menu",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into sys_menu (id, parent_id, ",
            "menu_type, name, url, ",
            "permission, menu_order, ",
            "visble, icon, remark, ",
            "create_time, create_by, ",
            "update_time, update_by)",
            "values (#{id,jdbcType=BIGINT}, #{parentId,jdbcType=BIGINT}, ",
            "#{menuType,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
            "#{permission,jdbcType=VARCHAR}, #{menuOrder,jdbcType=INTEGER}, ",
            "#{visble,jdbcType=CHAR}, #{icon,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{createBy,jdbcType=BIGINT}, ",
            "#{updateTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=BIGINT})"
    })
    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    @Select({
            "select",
            "id, parent_id, menu_type, name, url, permission, menu_order, visble, icon, remark, ",
            "create_time, create_by, update_time, update_by",
            "from sys_menu",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.wondersgroup.model.mapper.SysMenuMapper.BaseResultMap")
    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    @Update({
            "update sys_menu",
            "set parent_id = #{parentId,jdbcType=BIGINT},",
            "menu_type = #{menuType,jdbcType=CHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "url = #{url,jdbcType=VARCHAR},",
            "permission = #{permission,jdbcType=VARCHAR},",
            "menu_order = #{menuOrder,jdbcType=INTEGER},",
            "visble = #{visble,jdbcType=CHAR},",
            "icon = #{icon,jdbcType=VARCHAR},",
            "remark = #{remark,jdbcType=VARCHAR},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "create_by = #{createBy,jdbcType=BIGINT},",
            "update_time = #{updateTime,jdbcType=TIMESTAMP},",
            "update_by = #{updateBy,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysMenu record);

    @Select({
            "select permission from sys_menu m ,sys_role_menu rm,sys_user_role ur where m.id = rm.menu_id and rm.role_id = ur.role_id and ur.user_id = #{id,jdbcType=BIGINT}"})
    public List<String> queryPermissionByUserId(Long id);
}
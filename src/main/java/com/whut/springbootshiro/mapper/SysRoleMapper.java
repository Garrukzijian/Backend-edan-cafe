package com.whut.springbootshiro.mapper;



import com.whut.springbootshiro.form.SysRoleForm;
import com.whut.springbootshiro.model.SysRole;
import com.whut.springbootshiro.query.SysRoleQuery;
import com.whut.springbootshiro.vo.SysRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRoleVo> selectList(SysRoleQuery sysRoleQuery);

    int insertForm(SysRoleForm sysRoleForm);

    List<SysRole> selectListByUserId(Integer id);


    List<Integer> selectPermissionIds(Integer id);

    int deleteRolePermRel(@Param("roleId") Integer roleId);

    Integer batchInsertRolePermRel(@Param("roleId") Integer roleId, @Param("permissionId") List<Integer> permissionId);
}
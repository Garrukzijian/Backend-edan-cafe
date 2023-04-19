package com.whut.springbootshiro.service;




import com.whut.springbootshiro.common.Result;
import com.whut.springbootshiro.form.SysRoleForm;
import com.whut.springbootshiro.query.SysRoleQuery;

import java.util.List;

/**
 * @author
 * @create 2021-04-07 17:37
 */
public interface SysRoleService {

    Result selectList(SysRoleQuery sysRoleQuery);

    Result addRole(SysRoleForm sysRoleForm);

    Result updateRole(SysRoleForm sysRoleForm);

    Result allRole();

    Result userRole(Integer id);

    Result setRoles(Integer userId, List<Integer> roleId);

    List<String> queryUserRolesTag(Integer id);

    Result getPermissionIds(Integer id);

    Result setRolePermission(Integer roleId, List<Integer> permissionId);

    Object deleteById(Integer id);
}

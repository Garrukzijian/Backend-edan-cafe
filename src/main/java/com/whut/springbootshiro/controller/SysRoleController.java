package com.whut.springbootshiro.controller;



import com.whut.springbootshiro.annotation.MyLog;
import com.whut.springbootshiro.common.validator.ValidatorUtil;
import com.whut.springbootshiro.form.SysRoleForm;
import com.whut.springbootshiro.query.SysRoleQuery;
import com.whut.springbootshiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author
 * @create 2021-04-07 17:35
 */
@RestController
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @MyLog("获取到角色页面的这个接口")
    @RequestMapping("page")
    public Object page(SysRoleQuery sysRoleQuery) {
        return sysRoleService.selectList(sysRoleQuery);
    }

    @MyLog("添加角色")
    @RequestMapping("add")
    public Object addRole(SysRoleForm sysRoleForm) {
        //对数据进行校验，然后来进行之后的操作
        ValidatorUtil.validator(sysRoleForm);
        return sysRoleService.addRole(sysRoleForm);
    }

    @MyLog("更新角色")
    @PostMapping("update")
    public Object updateRole(SysRoleForm sysRoleForm) {
        //对数据进行校验，然后来进行之后的操作
        ValidatorUtil.validator(sysRoleForm);
        return sysRoleService.updateRole(sysRoleForm);
    }

    @MyLog("删除角色通过id")
    @PostMapping("deleteById")
    public Object deleteById(Integer id) {
        return sysRoleService.deleteById(id);
    }

    @GetMapping("all")
    public Object allRole() {
        return sysRoleService.allRole();
    }

    @PostMapping("userRoles")
    public Object userRole(@RequestParam("userId") Integer id) {
        //对数据进行校验，然后来进行之后的操作
        return sysRoleService.userRole(id);
    }

    @PostMapping("setRole")
    public Object setRole(@RequestParam("userId") Integer userId, @RequestParam(value = "roleId") Integer[] roleId) {
        List<Integer> integers = Arrays.asList(roleId);
        return sysRoleService.setRoles(userId, integers);
    }


    @RequestMapping("permissionIds")
    public Object permissionIds(Integer id) {
        return sysRoleService.getPermissionIds(id);
    }

    /**
     * 批量的设置这个权限的设置
     *
     * @param roleId       角色id
     * @param permissionId 权限id
     * @return 返回一个结果
     */
    @RequestMapping("setRolePermission")
    public Object setRolePermission(@RequestParam("roleId") Integer roleId, @RequestParam("permissionId") List<Integer> permissionId) {
        return sysRoleService.setRolePermission(roleId, permissionId);
    }


}

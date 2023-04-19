package com.whut.springbootshiro.model;

public class SysUserRoleRel {
    private Integer userId;

    private Integer roleId;

    public SysUserRoleRel() {
    }

    public SysUserRoleRel(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
package com.whut.springbootshiro.service;


import com.whut.springbootshiro.common.Result;
import com.whut.springbootshiro.form.SysUserForm;
import com.whut.springbootshiro.form.SysUserRegisterForm;
import com.whut.springbootshiro.query.SysUserQuery;

/**
 * @author
 * @create 2021-04-05 22:53
 */
public interface SysUserService {
    Result queryUser(String username, String password);

    Object queryPage(SysUserQuery sysUserQuery);

    Result addUser(SysUserForm sysUserForm);

    Result resetPwd(Integer id);

    Result updatePassword(Integer id, String newPassword);

    Result updateHeaderImg(String img);

    Object getUserById(Integer id);

    Object deleteUserById(Integer id);

    Object updateUserInfo(SysUserForm sysUserForm);

    Object updateCurUserInfo(SysUserForm sysUserForm);

    Object registerUser(SysUserRegisterForm sysUserRegisterForm);
}

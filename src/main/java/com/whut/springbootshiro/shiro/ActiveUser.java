package com.whut.springbootshiro.shiro;

import com.whut.springbootshiro.model.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @Description: 对 用户 角色 权限包装类
 * @author: Todd Ding
 * @date 2020-11-30 11:51
 */
@Data
public class ActiveUser {
    /**
     * 当前认证用户
     */
    private SysUser sysUser;
    /**
     * 用户真实名称
     */
    private String realname;

    private String img;


    /**
     * 用户所有的角色
     */
    private List<String> roles;
    /**
     * 用户所有的权限
     */
    private List<String> permissions;

}

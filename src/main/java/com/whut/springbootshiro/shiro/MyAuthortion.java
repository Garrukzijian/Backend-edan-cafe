package com.whut.springbootshiro.shiro;



import com.whut.springbootshiro.jwt.JWTToken;
import com.whut.springbootshiro.jwt.JWTUtil;
import com.whut.springbootshiro.mapper.SysUserMapper;
import com.whut.springbootshiro.model.SysUser;
import com.whut.springbootshiro.service.SysPermissionService;
import com.whut.springbootshiro.service.SysRoleService;
import com.whut.springbootshiro.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 认证鉴权器
 * @author: Todd Ding
 * @date 2020-11-30 11:50
 */
public class MyAuthortion extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;


    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 判断token是否事我们的这个jwttoekn
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
    /**
     * 用户认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的
        String tokenStr = (String) token.getCredentials();
        Integer userId = JWTUtil.getUserId(tokenStr);
        String username = JWTUtil.getUsername(tokenStr);
        SysUser sysUser1 = sysUserMapper.selectByPrimaryKey(userId);
        // 根据用户名和密码没有查询到数据  则直接返回null
        if (sysUser1 == null) {
            throw new AuthenticationException("用户不存在！");
        }
        if(!JWTUtil.verify(tokenStr,username,sysUser1.getLoginPassword())){
            throw new AuthenticationException("token校验不通过！");
        }

        String realname = sysUser1.getRealname();

        // 根据用户ID 查询用户所有的角色标识
        List<String> roleTags = sysRoleService.queryUserRolesTag(sysUser1.getId());
        // 根据用户ID 查询用户所有的权限标识
        List<String> permissionTags = sysPermissionService.queryUserPermissionTags(sysUser1.getId());

        ActiveUser activeUser = new ActiveUser();
        activeUser.setSysUser(sysUser1);
        activeUser.setRealname(realname);
        activeUser.setRoles(roleTags);
        activeUser.setImg(sysUser1.getImg());
        activeUser.setPermissions(permissionTags);
        // shiro自己校验密码
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activeUser, tokenStr, realname);
        return authenticationInfo;
    }

    /**
     * 用户鉴权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 得到用户名
        Integer userId = JWTUtil.getUserId(principals.toString());
        // 根据用户ID 查询用户所有的角色标识
        List<String> roleTags = sysRoleService.queryUserRolesTag(userId);
        List<String> permissionTags = sysPermissionService.queryUserPermissionTags(userId);

        //在这个地方进行返回的验证信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleTags);
        simpleAuthorizationInfo.addStringPermissions(permissionTags);
        return simpleAuthorizationInfo;
    }



}

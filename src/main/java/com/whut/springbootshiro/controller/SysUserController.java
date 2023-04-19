package com.whut.springbootshiro.controller;


import cn.hutool.core.util.StrUtil;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import com.whut.springbootshiro.annotation.MyLog;
import com.whut.springbootshiro.common.CodeMsg;
import com.whut.springbootshiro.common.Constant;
import com.whut.springbootshiro.common.Result;
import com.whut.springbootshiro.common.validator.ValidatorUtil;
import com.whut.springbootshiro.form.SysUserForm;
import com.whut.springbootshiro.form.SysUserRegisterForm;
import com.whut.springbootshiro.jwt.JWTToken;
import com.whut.springbootshiro.jwt.JWTUtil;
import com.whut.springbootshiro.mapper.SysUserMapper;
import com.whut.springbootshiro.model.SysUser;
import com.whut.springbootshiro.query.SysUserQuery;
import com.whut.springbootshiro.service.SysPermissionService;
import com.whut.springbootshiro.service.SysRoleService;
import com.whut.springbootshiro.service.SysUserService;
import com.whut.springbootshiro.shiro.ActiveUser;
import com.whut.springbootshiro.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户模块使用时使用的模块层
 *
 * @author
 * @create 2021-04-05 23:03
 */
@RestController
@RequestMapping("sysuser")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private Map<String,String> verCodeMap;



    /**
     * 使用登录名称还有密码来进行登录
     * 登录，会被直接放行，然后执行之后的方法，所以一些数据还是需要获取得到的
     *
     * @param username 名称
     * @param password 密码
     * @return 最后的一个结果
     */
    @PostMapping("login")
    public Object login(String username, String password) {
//        String s = verCodeMap.get(key);
//        if (Objects.isNull(s)){
//            return new Result(CodeMsg.CODE_INVALID);
//        }
//        if (!code.toLowerCase().equals(s.toLowerCase())){
//            return new Result(CodeMsg.CODE_ERROR);
//        }
        /*对传输过来的密码进行加密！！！*/
        Md5Hash md5Hash = new Md5Hash(password, Constant.MD5_SALT, 2);
        SysUser sysUser = sysUserMapper.selectUserByNameAndPwd(username, md5Hash.toString());
        if (sysUser == null) {
            return new Result(CodeMsg.USER_USER_PASSWORD_ERROR);
        }
        if (!sysUser.getLoginPassword().equals(md5Hash.toString())) {
            return new Result(CodeMsg.USER_USER_PASSWORD_ERROR);
        }
        sysUser.setLoginPassword(null);
        // 加密签名
        String token = JWTUtil.sign(sysUser.getId(),username, md5Hash.toString());
        JWTToken jwtToken = new JWTToken(token);
        String token1 = jwtToken.getToken();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        // 根据用户ID 查询用户所有的角色标识
        List<String> roleTags = sysRoleService.queryUserRolesTag(sysUser.getId());
        // 根据用户ID 查询用户所有的权限标识
        List<String> permissionTags = sysPermissionService.queryUserPermissionTags(sysUser.getId());

        ActiveUser activeUser = new ActiveUser();
        activeUser.setSysUser(sysUser);
        activeUser.setRealname(sysUser.getRealname());
        activeUser.setRoles(roleTags);
        activeUser.setImg(sysUser.getImg());
        activeUser.setPermissions(permissionTags);
        Date expTime = JWTUtil.getExpTime(token);
        String dateFormat = DateUtil.getDateFormat(expTime, DateUtil.FULL_TIME_PATTERN);
        stringObjectHashMap.put("token", token1);
        stringObjectHashMap.put("user", activeUser);
        stringObjectHashMap.put("exp", dateFormat);
        return new Result(stringObjectHashMap);
    }

    @ResponseBody
    @GetMapping("/captcha")
    public Object captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("key",key);
        stringStringHashMap.put("img",specCaptcha.toBase64());
        //存放到内存当中
        verCodeMap.put(key,verCode);
        return stringStringHashMap;
    }


    /**
     * @return java.lang.Object
     * @Author Lei 加上注释之后，会有报错，那么进行之后的修改。设置的事情
     * @Description
     * @Date 10:53 2022/1/5
     * @Param []
     **/
    @GetMapping("logout")
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        /*返回默认的登录的数据模块，然后在这个地方*/
        return new Result();
    }


    @MyLog("获取到页面的接口")
    @PostMapping("page")
    public Object userList(SysUserQuery sysUserQuery) {
        return sysUserService.queryPage(sysUserQuery);
    }

    @MyLog("通过用户的id来获取信息")
    @PostMapping("getUserById")
    public Object getUserById(Integer id) {
        return sysUserService.getUserById(id);
    }

    @MyLog("删除用户信息通过id")
    @PostMapping("deleteUserById")
    public Object deleteUserById(Integer id) {
        return sysUserService.deleteUserById(id);
    }

    @MyLog("更新用户的信息")
    @PostMapping("updateUserInfo")
    public Object updateUserInfo(SysUserForm sysUserForm) {
        //进行数据格式的校验，然后得到之后的数据。
        ValidatorUtil.validator(sysUserForm);
        return sysUserService.updateUserInfo(sysUserForm);
    }
    @MyLog("更新当前登录的用户信息")
    @PostMapping("updateCurUserInfo")
    public Object updateCurUserInfo(SysUserForm sysUserForm) {
        //进行数据格式的校验，然后得到之后的数据。
        ValidatorUtil.validator(sysUserForm);
        return sysUserService.updateCurUserInfo(sysUserForm);
    }

    /**
     * 这个是新增一个用户，然后放到这个这边
     *
     * @param sysUserForm
     * @return
     */

    @MyLog("添加用户的信息")
    @PostMapping("add")
    public Object addUser(SysUserForm sysUserForm) {
        //进行数据格式的校验，然后得到之后的数据。
        ValidatorUtil.validator(sysUserForm);
        return sysUserService.addUser(sysUserForm);
    }

    @PostMapping("register")
    public Object registerUser(SysUserRegisterForm sysUserRegisterForm) {
        //进行数据格式的校验，然后得到之后的数据。
        ValidatorUtil.validator(sysUserRegisterForm);
        return sysUserService.registerUser(sysUserRegisterForm);
    }
    /**
     * 重置密码的操作
     *
     * @param id 前端传递过来的一个数据的id值，然后进行之后的操作
     * @return
     */
    @MyLog("重置密码")
    @PostMapping("reset")
    public Object reset(Integer id) {
        return sysUserService.resetPwd(id);
    }


    @PostMapping("updatePassword")
    public Object updatePassword(String password, String newPassword) {
        //校验当前密码还有实际的密码
        Subject subject = SecurityUtils.getSubject();
        ActiveUser user = (ActiveUser) subject.getPrincipal();
        String loginPassword = user.getSysUser().getLoginPassword();
        Md5Hash md5Hash = new Md5Hash(password, Constant.MD5_SALT, 2);
        if (!StrUtil.equals(loginPassword, md5Hash.toString())) {
            return new Result(CodeMsg.USER_UPDATE_PASSWORD_ERROR);
        }
        Md5Hash md5Hash1 = new Md5Hash(newPassword, Constant.MD5_SALT, 2);
        Result result = sysUserService.updatePassword(user.getSysUser().getId(), md5Hash1.toString());
        if (result.getCode().equals(200)) {
            subject.logout();//退出操作，进行之后的操作
        }
        return result;
    }

    @MyLog("改变图像")
    @RequestMapping("updateHeaderImg")
    public Object updateHeaderImg(String img) {
//        直接改变当前用户的图片的地址
        Result result = sysUserService.updateHeaderImg(img);
        return result;            //创建新的最后一个结果，然后就是可以设置这个结果
    }

}

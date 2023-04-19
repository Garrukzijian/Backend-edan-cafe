package com.whut.springbootshiro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.whut.springbootshiro.common.CodeMsg;
import com.whut.springbootshiro.common.Constant;
import com.whut.springbootshiro.common.Result;
import com.whut.springbootshiro.form.SysUserForm;
import com.whut.springbootshiro.form.SysUserRegisterForm;
import com.whut.springbootshiro.mapper.SysUserMapper;
import com.whut.springbootshiro.mapper.SysUserRoleRelMapper;
import com.whut.springbootshiro.model.SysUser;
import com.whut.springbootshiro.model.SysUserRoleRel;
import com.whut.springbootshiro.query.SysUserQuery;
import com.whut.springbootshiro.service.SysUserService;
import com.whut.springbootshiro.shiro.ActiveUser;
import com.whut.springbootshiro.vo.SysUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author
 * @create 2021-04-05 22:53
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleRelMapper sysUserRoleRelMapper;

    @Resource
    private Map<String,String> verCodeMap;


    /**
     * 根据用户名和密码来查询数据
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result queryUser(String username, String password) {
        SysUser user = sysUserMapper.selectUserByNameAndPwd(username, password);
        if (user == null) {
            return new Result(CodeMsg.USER_USER_PASSWORD_ERROR);
        }
        //默认使用这个正确的代码，然后使用这个数据当中这个返回的一个数据放到这个上面
        return new Result(user);
    }

    @Override
    public Object queryPage(SysUserQuery sysUserQuery) {
        Page<SysUser> objects = PageHelper.startPage(sysUserQuery.getPage(), sysUserQuery.getLimit());
        List<SysUserVo> sysUserVoList = sysUserMapper.selectListByUserQuery(sysUserQuery);
        return new Result(objects.toPageInfo());
    }


    @Override
    public Result addUser(SysUserForm sysUserForm) {
        //业务操作
        SysUserQuery sysUserQuery = new SysUserQuery();
        //用户名不可以重复
        sysUserQuery.setLoginName(sysUserForm.getLoginName());
        SysUserVo sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        if (sysUserVo != null) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.USER_LOGIN_NAME_EXIST_ERROR);
        }
        //手机号不可以重复
        sysUserQuery = new SysUserQuery();
        sysUserQuery.setPhone(sysUserForm.getPhone());
        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        if (sysUserVo != null) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.USER_PHONE_EXIST_ERROR);
        }
        //身份证不可以重复
        sysUserQuery = new SysUserQuery();
        sysUserQuery.setIdCard(sysUserForm.getIdCard());
        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        if (sysUserVo != null) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.USER_ID_CARD_EXIST_ERROR);
        }
        //对默认密码进行加密，然后设置到这个里面去
        Md5Hash md5Hash = new Md5Hash(Constant.DEFAULT_PASSWORD, Constant.MD5_SALT, 2);
        sysUserForm.setLoginPassword(md5Hash.toString());
        sysUserMapper.insertSysUserForm(sysUserForm);
        return new Result();
    }

    @Override
    public Object registerUser(SysUserRegisterForm sysUserRegisterForm) {
        String s = verCodeMap.get(sysUserRegisterForm.getKey());
        if (Objects.isNull(s)){
            return new Result(CodeMsg.CODE_INVALID);
        }
        if (!sysUserRegisterForm.getCode().toLowerCase().equals(s.toLowerCase())){
            return new Result(CodeMsg.CODE_ERROR);
        }
        //业务操作
        SysUserQuery sysUserQuery = new SysUserQuery();
        //用户名不可以重复
        sysUserQuery.setLoginName(sysUserRegisterForm.getLoginName());
        SysUserVo sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        if (sysUserVo != null) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.USER_LOGIN_NAME_EXIST_ERROR);
        }
        //手机号不可以重复
        sysUserQuery = new SysUserQuery();
        sysUserQuery.setPhone(sysUserRegisterForm.getPhone());
        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        if (sysUserVo != null) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.USER_PHONE_EXIST_ERROR);
        }
        //身份证不可以重复
        sysUserQuery = new SysUserQuery();
        sysUserQuery.setIdCard(sysUserRegisterForm.getIdCard());
        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        if (sysUserVo != null) {
            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
            return new Result(CodeMsg.USER_ID_CARD_EXIST_ERROR);
        }
        //对默认密码进行加密，然后设置到这个里面去
        Md5Hash md5Hash = new Md5Hash(sysUserRegisterForm.getLoginPassword(), Constant.MD5_SALT, 2);
        sysUserRegisterForm.setLoginPassword(md5Hash.toString());

        //插入一条数据是以自己传递的
        SysUserForm sysUserForm = new SysUserForm();
        BeanUtil.copyProperties(sysUserRegisterForm,sysUserForm,false);
        //默认的数据在这个地方进行设置的

        sysUserForm.setRealname("新增用户");
        sysUserForm.setSex(1);
        sysUserForm.setAddress("未填写");

        sysUserMapper.insertSysUserForm(sysUserForm);
        //通过查询得到key值，然后通过key值进行之后的设置权限的id;
        sysUserQuery.setLoginName(sysUserRegisterForm.getLoginName());
        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
        //当前用户的id，然后需要将权限设置一个标志位,这个是之后来做的呢
        Integer id = sysUserVo.getId();
        //添加进权限
        SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
        sysUserRoleRel.setUserId(id);
        //这个id需要从后台来获取到呢
        sysUserRoleRel.setRoleId(6);
        sysUserRoleRelMapper.insert(sysUserRoleRel);
        return new Result();
    }

    @Override
    public Object updateUserInfo(SysUserForm sysUserForm) {
//        //业务操作
//        SysUserQuery sysUserQuery = new SysUserQuery();
//        //用户名不可以重复
//        sysUserQuery.setLoginName(sysUserForm.getLoginName());
//        SysUserVo sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
//        if (sysUserVo != null) {
//            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
//            return new Result(CodeMsg.USER_LOGIN_NAME_EXIST_ERROR);
//        }
//        //手机号不可以重复
//        sysUserQuery = new SysUserQuery();
//        sysUserQuery.setPhone(sysUserForm.getPhone());
//        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
//        if (sysUserVo != null) {
//            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
//            return new Result(CodeMsg.USER_PHONE_EXIST_ERROR);
//        }
//        //身份证不可以重复
//        sysUserQuery = new SysUserQuery();
//        sysUserQuery.setIdCard(sysUserForm.getIdCard());
//        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdcard(sysUserQuery);
//        if (sysUserVo != null) {
//            /*这个就是设置这个用户名称已经存在的错误，然后放到这个地方进行设置的*/
//            return new Result(CodeMsg.USER_ID_CARD_EXIST_ERROR);
//        }
        sysUserMapper.updateBysysUserForm(sysUserForm);
        return new Result();
    }


    @Override
    public Object updateCurUserInfo(SysUserForm sysUserForm) {
        Integer id = sysUserForm.getId();           //当前用户的id
        sysUserMapper.updateBysysUserForm(sysUserForm);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
        principal.setRealname(sysUserForm.getRealname());
        principal.setImg(sysUserForm.getImg());
        principal.setSysUser(sysUser);
        return new Result(principal);
    }

    /**
     * 修改密码的操作
     *
     * @param id
     * @return
     */
    @Override
    public Result resetPwd(Integer id) {
        Md5Hash md5Hash = new Md5Hash(Constant.DEFAULT_PASSWORD, Constant.MD5_SALT, 2);
        //根据id查出来这个用户的对象
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        sysUser.setLoginPassword(md5Hash.toString());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return new Result();
    }

    @Override
    public Result updatePassword(Integer id, String newPassword) {
        sysUserMapper.updatePasword(id, newPassword);
        return new Result();
    }

    @Override
    public Result updateHeaderImg(String img) {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
        Integer id = principal.getSysUser().getId();
        sysUserMapper.updateUserHeaderImg(id, img);
        return new Result();
    }

    @Override
    public Object getUserById(Integer id) {
        return new Result(sysUserMapper.selectByPrimaryKey(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object deleteUserById(Integer id) {
        //首先删除掉删除自己的角色，然后这层关系断了就行了，然后需要删除的就是
        try {
            sysUserRoleRelMapper.deleteUserRoles(id);
            sysUserMapper.deleteByPrimaryKey(id);
            return new Result();
        } catch (Exception e) {
            return new Result(CodeMsg.DELETE_USER_ERROR);
        }
    }
}

package com.whut.springbootshiro.mapper;


;
import com.whut.springbootshiro.form.SysUserForm;
import com.whut.springbootshiro.model.SysUser;
import com.whut.springbootshiro.query.SysUserQuery;
import com.whut.springbootshiro.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectUserByNameAndPwd(@Param("username") String username, @Param("password") String password);

    List<SysUserVo> selectListByUserQuery(SysUserQuery sysUserQuery);

    SysUserVo selectUserByNameOrPhoneOrIdcard(SysUserQuery sysUserQuery);

    void insertSysUserForm(SysUserForm sysUserForm);

    int updatePasword(@Param("id") Integer id, @Param("newPassword") String newPassword);

    int updateUserHeaderImg(@Param("id") Integer id, @Param("img") String img);

    void updateBysysUserForm(SysUserForm sysUserForm);

    SysUser selectByName(String custName);

    int deleteByName(String custName);
}
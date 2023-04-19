package com.whut.springbootshiro.mapper;



import com.whut.springbootshiro.form.SysPermissionForm;
import com.whut.springbootshiro.model.SysPermission;
import com.whut.springbootshiro.query.SysPermissionQuery;
import com.whut.springbootshiro.vo.SysPermissionMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermissionMenuVo> selectLeftMenuByUserId(@Param("userId") Integer userId, @Param("type") Integer type, @Param("parentId") Integer parentId);

    List<SysPermissionMenuVo> selectList(SysPermissionQuery sysPermissionQuery);

    int insertForm(SysPermissionForm sysPermissionForm);

    List<Integer> selectAllChildId(@Param("ids") List<Integer> ids);

    int batchDeleteIds(@Param("ids") List<Integer> ids);

    int batchDeletePermIds(@Param("ids") List<Integer> ids);


}
package com.whut.springbootshiro.service;





import com.whut.springbootshiro.common.Result;
import com.whut.springbootshiro.form.SysPermissionForm;
import com.whut.springbootshiro.query.SysPermissionQuery;

import java.util.List;

/**
 * @author
 * @create 2021-04-07 20:39
 */
public interface SysPermissionService {
    Result getLeftMenu();

    List<String> queryUserPermissionTags(Integer id);

    Result getPageList(SysPermissionQuery sysPermissionQuery);

    Result getAll();

    Result add(SysPermissionForm sysPermissionForm);

    Result update(SysPermissionForm sysPermissionForm);

    Result delete(Integer id);

    Object getAllWithChild();

    Object getCurPerm();
}

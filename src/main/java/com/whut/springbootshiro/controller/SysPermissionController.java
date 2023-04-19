package com.whut.springbootshiro.controller;



import com.whut.springbootshiro.annotation.MyLog;
import com.whut.springbootshiro.common.validator.ValidatorUtil;
import com.whut.springbootshiro.form.SysPermissionForm;
import com.whut.springbootshiro.query.SysPermissionQuery;
import com.whut.springbootshiro.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @create 2021-04-07 20:36
 */
@RestController
@RequestMapping("permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 获取当前的左侧菜单
     *
     * @return
     */
    @MyLog("获取左边菜单栏的页面")
    @GetMapping("leftMenu")
    @ResponseBody
    public Object getUserMenu() {
        return sysPermissionService.getLeftMenu();
    }

    @MyLog("获取当前权限")
    @GetMapping("getCurPerm")
    public Object getCurPerm() {
        return sysPermissionService.getCurPerm();
    }


    @MyLog("获取所有的权限")
    @GetMapping("all")
    public Object getAll() {
        return sysPermissionService.getAll();
    }
    @MyLog("获取权限，带有树形结构带有孩子的结构")
    @GetMapping("allWithChild")
    public Object getAllWithChild() {
        return sysPermissionService.getAllWithChild();
    }



    /**
     * 分页查询这个权限列表类
     *
     * @param sysPermissionQuery
     * @return
     */
    @MyLog("获取权限的页面的数据")
    @RequestMapping("page")
    public Object getPageList(SysPermissionQuery sysPermissionQuery) {
        return sysPermissionService.getPageList(sysPermissionQuery);
    }

    @MyLog("添加权限")
    @RequestMapping("add")
    public Object add(SysPermissionForm sysPermissionForm) {
        ValidatorUtil.validator(sysPermissionForm);
        return sysPermissionService.add(sysPermissionForm);
    }

    @MyLog("更新权限")
    @RequestMapping("update")
    public Object update(SysPermissionForm sysPermissionForm) {
        ValidatorUtil.validator(sysPermissionForm);
        return sysPermissionService.update(sysPermissionForm);
    }


    @MyLog("删除权限")
    @RequestMapping("delete")
    public Object delete(Integer id) {
        return sysPermissionService.delete(id);
    }


}

package com.whut.springbootshiro.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.whut.springbootshiro.common.Constant;
import com.whut.springbootshiro.common.Result;
import com.whut.springbootshiro.form.SysPermissionForm;
import com.whut.springbootshiro.mapper.SysPermissionMapper;
import com.whut.springbootshiro.mapper.SysRolePerRelMapper;
import com.whut.springbootshiro.model.SysPermission;
import com.whut.springbootshiro.model.SysRolePerRel;
import com.whut.springbootshiro.query.SysPermissionQuery;
import com.whut.springbootshiro.service.SysPermissionService;
import com.whut.springbootshiro.shiro.ActiveUser;
import com.whut.springbootshiro.vo.SysPermissionMenuVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2021-04-07 20:39
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private SysRolePerRelMapper sysRolePerRelMapper;

    @Override
    public Result getLeftMenu() {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
//        principal.getSysUser().getId();
        //获取菜单获取的就是一级菜单，然后在获取的就是之后的菜单
        List<SysPermissionMenuVo> sysPermissionMenuVo = sysPermissionMapper.selectLeftMenuByUserId(principal.getSysUser().getId(), Constant.PERMISSION_TYPE_MENU, Constant.MENU_LV1);
        for (int i = 0; i < sysPermissionMenuVo.size(); i++) {
            //获取一级菜单之后的菜单，然后设置就是这个一个权限的设置
            List<SysPermissionMenuVo> temp = sysPermissionMapper.selectLeftMenuByUserId(principal.getSysUser().getId(), Constant.PERMISSION_TYPE_MENU, sysPermissionMenuVo.get(i).getId());
            sysPermissionMenuVo.get(i).setChildren(temp);
        }
        return new Result(sysPermissionMenuVo);
    }


    @Override
    public Object getCurPerm() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        Object data = getLeftMenu().getData();
        Subject subject = SecurityUtils.getSubject();

        stringObjectHashMap.put("leftMenu",data);
        stringObjectHashMap.put("curUser",(ActiveUser)subject.getPrincipal());
        return new Result(stringObjectHashMap);
    }

    @Override
    public List<String> queryUserPermissionTags(Integer id) {
        List<SysPermissionMenuVo> sysPermissionMenuVos = sysPermissionMapper.selectLeftMenuByUserId(id, null, null);
        ArrayList<String> strings = new ArrayList<>();
        for (SysPermissionMenuVo sysPermissionMenuVo : sysPermissionMenuVos) {
            strings.add(sysPermissionMenuVo.getTag());
        }
        return strings;
    }

    @Override
    public Result getPageList(SysPermissionQuery sysPermissionQuery) {
        Page<SysPermissionMenuVo> objects = PageHelper.startPage(sysPermissionQuery.getPage(), sysPermissionQuery.getLimit());
        sysPermissionMapper.selectList(sysPermissionQuery);
        return new Result(objects.toPageInfo());
    }

    @Override
    public Result getAll() {
        List<SysPermissionMenuVo> sysPermissionMenuVos = sysPermissionMapper.selectList(null);
        return new Result(sysPermissionMenuVos);
    }

    @Override
    public Object getAllWithChild() {
        //查询出所有的权限
        List<SysPermissionMenuVo> sysPermissionMenuVos = sysPermissionMapper.selectList(null);
        //进行对权限的划分映射，也就说出现的应该就是权限的id值对应一个权限值，这个映射关系起到了重要的作用
        Map<Integer, SysPermissionMenuVo> collect1 = sysPermissionMenuVos.stream()
                .collect(Collectors.toMap(SysPermission::getId, Function.identity()));
        //进行遍历操作，执行之后的数据变化
        for (int i = 0; i < sysPermissionMenuVos.size(); i++) {
            //如果是根目录
            SysPermissionMenuVo sysPermissionMenuVo = sysPermissionMenuVos.get(i);
            if (sysPermissionMenuVo.getParentId() == 0) {
                continue;
            }
            //将自己添加到我们的树当中，然后开始之后的操作
            Integer parentId = sysPermissionMenuVo.getParentId();
            SysPermissionMenuVo parent = collect1.get(parentId);
            List<SysPermissionMenuVo> children = parent.getChildren();
            if (children == null) {
                ArrayList<SysPermissionMenuVo> sysPermissionMenuVos1 = new ArrayList<>();
                sysPermissionMenuVos1.add(sysPermissionMenuVo);
                parent.setChildren(sysPermissionMenuVos1);
            } else {
                children.add(sysPermissionMenuVo);
            }
        }
        //最终返回我们的第一级的列表，然后完成对树形结构的构造
        List<SysPermissionMenuVo> collect = sysPermissionMenuVos.stream().filter(e -> e.getParentId() == 0).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Result add(SysPermissionForm sysPermissionForm) {
        sysPermissionMapper.insertForm(sysPermissionForm);
//        因为在这个位置上，管理员，具有绝对的权限，我们希望的就是可以得到这个权限，
        Integer id = sysPermissionForm.getId();
        SysRolePerRel sysRolePerRel = new SysRolePerRel();
        sysRolePerRel.setRoleId(1);
        sysRolePerRel.setPerId(id);
        sysRolePerRelMapper.insert(sysRolePerRel);      //将管理员设置的权限设置到这个表中
        return new Result();
    }

    @Override
    public Result update(SysPermissionForm sysPermissionForm) {
        SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(sysPermissionForm.getId());
        sysPermission.setHref(sysPermissionForm.getHref());
        sysPermission.setIcon(sysPermissionForm.getIcon());
        sysPermission.setParentId(sysPermissionForm.getParentId());
        sysPermission.setSort(sysPermissionForm.getSort());
        sysPermission.setSpread(sysPermissionForm.getSpread().equals(1));
        sysPermission.setTag(sysPermissionForm.getTag());
        sysPermission.setType(sysPermissionForm.getType());
        sysPermission.setTitle(sysPermissionForm.getTitle());
        sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
        return new Result();
    }

    @Override
    public Result delete(Integer id) {
        //需要删除这个树的子权限还有子子权限都需要进行删除，那么就需要进行之后的删除了。
        //所有的id，需要进行删除的一些id的列表根据传递过来的id，然后来进行删除之后的数据
        //删除这一级之后下面的所有的权限的列表
        ArrayList<Integer> ids = new ArrayList<>();
        //二级
        ids.add(id);
        //得到下面一级的操作的一个操作的数据
        List<Integer> childIds = sysPermissionMapper.selectAllChildId(ids);
        while (childIds.size() != 0) {
            ids.addAll(childIds);
            //继续得到下面一级
            childIds = sysPermissionMapper.selectAllChildId(childIds);
        }
        sysPermissionMapper.batchDeleteIds(ids);
        sysPermissionMapper.batchDeletePermIds(ids);
        return new Result();
    }


}

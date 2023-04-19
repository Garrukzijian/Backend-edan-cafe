package com.whut.springbootshiro.vo;



import com.whut.springbootshiro.model.SysPermission;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @create 2021-04-07 20:41
 */
@Data
public class SysPermissionMenuVo extends SysPermission {
    /**
     * 子菜单
     */
    private List<SysPermissionMenuVo> children;


    private String checkArr = "0";
}

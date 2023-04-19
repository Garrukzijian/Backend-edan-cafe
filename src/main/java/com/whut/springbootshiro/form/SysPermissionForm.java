package com.whut.springbootshiro.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author
 * @create 2021-04-07 23:57
 */
@Data
public class SysPermissionForm {

    private Integer id;

    @NotNull(message = "父权限不可以为空")
    private Integer parentId;

    @NotEmpty(message = "权限名称不能为空")
    private String title;


    @Length(max = 20, message = "权限图标最多就是20个字符")
    private String icon;

    @Length(max = 100, message = "链接地址最多就是100个字符")
    private String href;

    @Range(min = 0, max = 1, message = "是否展开只能是0~1")
    private Integer spread;

    @NotNull(message = "权限类型不可以为空")
    @Range(min = 1, max = 2, message = "权限类型只能是1~2")
    private Integer type;

    @NotNull(message = "权限标识不可以为空")
    @Length(max = 20, message = "权限标识只能是20字符")
    private String tag;

    private Integer sort;


}

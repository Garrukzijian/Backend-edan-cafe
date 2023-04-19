package com.whut.springbootshiro.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author
 * @create 2021-04-07 19:06
 */
@Data
public class SysRoleForm {


    private Integer id;     //角色id

    @NotEmpty(message = "角色名称不可以为空")
    @Length(max = 20, message = "角色名称长度不可以超过20")
    private String name;

    @NotEmpty(message = "角色标识不可以为空")
    @Length(max = 20, message = "角色标识长度不可以超过20")
    private String tag;

    @Length(max = 20, message = "描述长度不可以超过20")
    private String descp;

}

package com.whut.springbootshiro.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author
 * @create 2021-04-06 11:33
 */
@Data
public class SysUserForm {
    private Integer id;

    @NotEmpty(message = "登录名不可以为空")
    @Length(min = 5, max = 15, message = "登录名5~15位字符")
    private String loginName;
    //使用默认密码
    private String loginPassword;

    @NotEmpty(message = "手机号不可以为空")
    @Length(min = 10, max = 10, message = "手机号只能是11位字符")
    private String phone;

    @NotEmpty(message = "真实姓名不可以为空")
    private String realname;

    @NotEmpty(message = "身份证不可以为空")
    @Length(min = 18, max = 18, message = "身份证只能是18位")
    private String idCard;

    @NotNull(message = "性别不可以为空")
    @Range(min = 0, max = 1, message = "性别只能是男或者是女")
    private Integer sex;


    @NotNull(message = "地址不可以为空")
    private String address;

    private String img;


}

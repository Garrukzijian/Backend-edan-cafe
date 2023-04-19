package com.whut.springbootshiro.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lei
 * @create 2022-02-14 22:20
 */
@Data
public class SysUserRegisterForm  {
    @NotEmpty(message = "验证码关联不可以为空")
    private String key;
    @NotEmpty(message = "验证码不可以为空")
    private String code;

    @NotEmpty(message = "登录名不可以为空")
    @Length(min = 5, max = 15, message = "登录名5~15位字符")
    private String loginName;

    @NotEmpty(message = "密码不可以为空")
    private String loginPassword;

    @NotEmpty(message = "手机号不可以为空")
    @Length(min = 10, max = 10, message = "手机号只能是11位字符")
    private String phone;

    @NotEmpty(message = "身份证不可以为空")
    @Length(min = 18, max = 18, message = "身份证只能是18位")
    private String idCard;
}

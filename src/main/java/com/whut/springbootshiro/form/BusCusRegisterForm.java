package com.whut.springbootshiro.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lei
 * @create 2023-03-13 23:15
 */
@Data
public class BusCusRegisterForm extends BusCusForm {
    @NotEmpty(message = "邮件不可以为空")
    private String custPasswd;
}

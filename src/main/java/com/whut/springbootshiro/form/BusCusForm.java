package com.whut.springbootshiro.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lei
 * @create 2023-03-12 21:31
 */
@Data
public class BusCusForm {

    private Integer custId;
    @NotEmpty(message = "名称不可以为空")
    private String custName;
    @NotEmpty(message = "sur名称不可以为空")
    private String custSurname;
    @NotEmpty(message = "地区不可以为空")
    private String custDistirct;
    @NotEmpty(message = "街道不可以为空")
    private String custAddress;
    @NotEmpty(message = "邮编不可以为空")
    private String custZipcode;
    @NotEmpty(message = "城市不可以为空")
    private String custCity;
    @NotEmpty(message = "国家不可以为空")
    private String custCountry;
    @NotEmpty(message = "电话不可以为空")
    private String custPhone;
    @NotEmpty(message = "邮件不可以为空")
    private String custEmail;
}

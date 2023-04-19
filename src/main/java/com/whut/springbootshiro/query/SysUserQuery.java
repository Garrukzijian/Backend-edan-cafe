package com.whut.springbootshiro.query;

import lombok.Data;

/**
 * @author
 * @create 2021-04-06 10:55
 */
@Data
public class SysUserQuery extends Query {
    //用户姓名
    private String realname;
    //电话
    private String phone;
    //地址
    private String address;
    //登录名称
    private String loginName;
    //身份证
    private String idCard;
}

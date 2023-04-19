package com.whut.springbootshiro.common;

/**
 * @Description: 业务码 业务消息枚举
 * @author: Todd Ding
 * @date 2020-11-30 11:51
 */
public enum CodeMsg {

    SUCCESS(200, "操作成功"),
    ERROR(110, "程序员送外卖了！！！"),
    AUTH_ERROR(400, "没有相应的权限！！！"),
    CODE_ERROR(440, "验证码错误"),
    CODE_INVALID(441, "验证码失效"),

    ADD_ERROR(2001,"添加错误"),
    UPDATE_ERROR(2002,"修改错误"),
    DELETE_ERROR(2003,"删除错误"),


    USER_USER_PASSWORD_ERROR(4001001, "用户名或者密码错误!"),

    USER_LOGIN_NAME_EXIST_ERROR(4001002, "用户登录名已被使用!"),
    USER_PHONE_EXIST_ERROR(4001003, "用户手机号已被使用!"),
    USER_ID_CARD_EXIST_ERROR(4001004, "用户身份证号已被使用!"),
    USER_NOT_HAVE_PERMISSION_ERROR(4001005, "用户权限不足!"),
    USER_UPDATE_PASSWORD_ERROR(4001006, "修改失败,原密码不正确"),
    DELETE_USER_ERROR(4002001,"删除用户信息信息失败"),


    CAR_UPLOAD_IMG_ERROR(4003001, "汽车图片上传失败!"),

    ;
    public Integer code; // 业务码
    public String msg; // 业务消息

    CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

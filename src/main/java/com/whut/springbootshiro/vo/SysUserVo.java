package com.whut.springbootshiro.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.whut.springbootshiro.model.SysUser;
import lombok.Data;

import java.util.Date;

/**
 * @author
 * @create 2021-04-06 11:00
 */
@Data
public class SysUserVo extends SysUser {
    //这个模式就是按照年月日还有时分秒的一个模板就是使用的时
    //yyyy-MM-dd HH:mm:ss
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}

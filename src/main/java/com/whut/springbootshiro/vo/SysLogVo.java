package com.whut.springbootshiro.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whut.springbootshiro.model.SysLog;
import lombok.Data;

import java.util.Date;

/**
 * @author Lei
 * @create 2021-08-20 0:22
 */
@Data
public class SysLogVo extends SysLog {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;
}

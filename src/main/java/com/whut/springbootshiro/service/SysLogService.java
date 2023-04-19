package com.whut.springbootshiro.service;


import com.whut.springbootshiro.common.Result;
import com.whut.springbootshiro.query.SysLogQuery;

/**
 * @author Lei
 * @create 2021-08-20 0:16
 */
public interface SysLogService {
    Result page(SysLogQuery sysLogQuery);
}

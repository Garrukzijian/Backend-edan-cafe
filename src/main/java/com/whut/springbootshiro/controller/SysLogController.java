package com.whut.springbootshiro.controller;



import com.whut.springbootshiro.query.SysLogQuery;
import com.whut.springbootshiro.service.SysLogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lei
 * @create 2021-08-20 0:17
 */
@RestController
@RequestMapping("log")
public class SysLogController {

    @Resource
    private SysLogService sysLogService;


    //在这个地方加上自己的这个注解的时候，这个注解导致sysLogService为null
    @PostMapping("page")
    private Object page(SysLogQuery sysLogQuery){
        return sysLogService.page(sysLogQuery);
    }
}

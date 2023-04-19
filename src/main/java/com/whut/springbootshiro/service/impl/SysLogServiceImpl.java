package com.whut.springbootshiro.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.whut.springbootshiro.common.Result;
import com.whut.springbootshiro.mapper.SysLogMapper;
import com.whut.springbootshiro.query.SysLogQuery;
import com.whut.springbootshiro.service.SysLogService;
import com.whut.springbootshiro.vo.SysLogVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lei
 * @create 2021-08-20 0:16
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public Result page(SysLogQuery sysLogQuery) {
        Page<SysLogVo> busEquipVos = PageHelper.startPage(sysLogQuery.getPage(), sysLogQuery.getLimit());
        List<SysLogVo> busLabVos = sysLogMapper.selectList(sysLogQuery);
        return new Result(busEquipVos.toPageInfo());
    }
}

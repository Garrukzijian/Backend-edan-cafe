package com.whut.springbootshiro.mapper;

import com.whut.springbootshiro.model.SysLog;
import com.whut.springbootshiro.query.SysLogQuery;
import com.whut.springbootshiro.vo.SysLogVo;

import java.util.List;


public interface SysLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    List<SysLogVo> selectList(SysLogQuery sysLogQuery);
}
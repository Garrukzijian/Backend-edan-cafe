package com.whut.springbootshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whut.springbootshiro.model.Customer;
import com.whut.springbootshiro.model.Menu;

public interface MenuMapper extends BaseMapper<Menu> {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    Integer getMaxIntId();
}
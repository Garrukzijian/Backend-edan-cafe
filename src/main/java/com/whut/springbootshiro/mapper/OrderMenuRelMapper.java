package com.whut.springbootshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whut.springbootshiro.model.OrderMenuRel;

public interface OrderMenuRelMapper extends BaseMapper<OrderMenuRel> {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderMenuRel record);

    int insertSelective(OrderMenuRel record);

    OrderMenuRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderMenuRel record);

    int updateByPrimaryKey(OrderMenuRel record);
}
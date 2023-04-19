package com.whut.springbootshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whut.springbootshiro.model.Customer;
import com.whut.springbootshiro.model.Order;
import com.whut.springbootshiro.vo.MenuTopVo;
import com.whut.springbootshiro.vo.OrderListVo;

import java.math.BigDecimal;
import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Integer getMaxIntId();

    List<OrderListVo> selectCustOrderList(String name,String menuChiname,String menuEngname,String start,String end);

    List<OrderListVo> selectCustOrderListByOrderId(Integer orderId);

    List<MenuTopVo> top10();

    BigDecimal selectCustTotalOrderList(String name, String menuChiname, String menuEngname, String start, String end);
}
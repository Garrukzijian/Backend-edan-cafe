package com.whut.springbootshiro.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whut.springbootshiro.model.Payment;

public interface PaymentMapper extends BaseMapper<PaymentMapper> {
    int deleteByPrimaryKey(Integer paymentId);

    int insert(Payment record);

    int insertSelective(Payment record);

    Payment selectByPrimaryKey(Integer paymentId);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);

    Integer getMaxIntId();
}
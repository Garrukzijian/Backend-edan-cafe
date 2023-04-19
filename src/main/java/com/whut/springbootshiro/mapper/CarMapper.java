package com.whut.springbootshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whut.springbootshiro.model.Car;
import com.whut.springbootshiro.query.CarQuery;
import com.whut.springbootshiro.vo.CarVo;

import java.util.List;

public interface CarMapper extends BaseMapper<Car> {
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    List<CarVo> selectList(CarQuery carQuery);
}
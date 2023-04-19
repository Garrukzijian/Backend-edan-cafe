package com.whut.springbootshiro.service;

import com.whut.springbootshiro.form.BusCusForm;
import com.whut.springbootshiro.form.BusCusRegisterForm;
import com.whut.springbootshiro.form.BusMenuForm;
import com.whut.springbootshiro.query.BusCusQuery;
import com.whut.springbootshiro.query.BusMenuQuery;
import com.whut.springbootshiro.query.CarQuery;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author Lei
 * @create 2023-03-12 21:14
 */
public interface BusDataService {
    Object selectMenuList(BusMenuQuery busTagQuery);

    Object addMenu(BusMenuForm busMenuForm);

    Object updateMenu(BusMenuForm busMenuForm);

    Object deleteMenu(Integer id);

    Object selectCusList(BusCusQuery busCusQuery);

    Object addCus(BusCusForm busCusForm);

    Object updateCus(BusCusForm busCusForm);

    Object deleteCus(Integer id);

    Object register(BusCusRegisterForm busCusRegisterForm);

    Object addCar(String name, int menuId, int num);

    Object delCar(Integer id);

    Object pageCar(CarQuery carQuery);

    Object payCar(int custId, int[] menuList, int[] num, int discount, BigDecimal totalPrice);

    Object orderList(String name,String menuChiname,String menuEngname,String start,String end);

    void orderTxt(Integer orderId, HttpServletResponse response) throws IOException;

    Object top10();

    Object orderTotalList(String name, String menuChiname, String menuEngname, String startTime, String endTime);
}

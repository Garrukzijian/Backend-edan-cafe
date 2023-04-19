package com.whut.springbootshiro.controller;

import com.whut.springbootshiro.common.validator.ValidatorUtil;
import com.whut.springbootshiro.form.BusCusForm;
import com.whut.springbootshiro.form.BusCusRegisterForm;
import com.whut.springbootshiro.form.BusMenuForm;
import com.whut.springbootshiro.query.BusCusQuery;
import com.whut.springbootshiro.query.BusMenuQuery;
import com.whut.springbootshiro.query.CarQuery;
import com.whut.springbootshiro.service.BusDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


@RestController
@RequestMapping("data")
public class BusDataController {

    @Resource
    private BusDataService busDataService;
    
    @RequestMapping("/menu/page")
    public Object page(BusMenuQuery busTagQuery) {
        return busDataService.selectMenuList(busTagQuery);
    }

    @RequestMapping("/menu/add")
    public Object add(BusMenuForm busMenuForm) {
        ValidatorUtil.validator(busMenuForm);
        return busDataService.addMenu(busMenuForm);
    }
    
    @RequestMapping("/menu/update")
    public Object update(BusMenuForm busMenuForm) {
        ValidatorUtil.validator(busMenuForm);
        return busDataService.updateMenu(busMenuForm);
    }

    @RequestMapping("/menu/delete")
    public Object deleteMenu(Integer id) {
        return busDataService.deleteMenu(id);
    }
    
    
    
    
    
    
    
    
    @RequestMapping("/cus/page")
    public Object page(BusCusQuery busCusQuery) {
        return busDataService.selectCusList(busCusQuery);
    }

    @RequestMapping("/cus/add")
    public Object add(BusCusForm busCusForm) {
        ValidatorUtil.validator(busCusForm);
        return busDataService.addCus(busCusForm);
    }
    @RequestMapping("/cus/register")
    public Object register(BusCusRegisterForm busCusRegisterForm) {
        ValidatorUtil.validator(busCusRegisterForm);
        return busDataService.register(busCusRegisterForm);
    }

    @RequestMapping("/cus/update")
    public Object update(BusCusForm busCusForm) {
        ValidatorUtil.validator(busCusForm);
        return busDataService.updateCus(busCusForm);
    }


    @RequestMapping("/cus/delete")
    public Object deleteCus(Integer id) {
        return busDataService.deleteCus(id);
    }


    @RequestMapping("/car/add")
    public Object addCar(String name,int menuId,int num) {
        return busDataService.addCar(name,menuId,num);
    }


    @RequestMapping("/car/delete")
    public Object delCar(Integer id) {
        return busDataService.delCar(id);
    }


    @RequestMapping("/car/page")
    public Object pageCar(CarQuery carQuery) {
        return busDataService.pageCar(carQuery);
    }


    @RequestMapping("/car/pay")
    public Object payCar(int custId, int[] menuList, int[] num, int discount, BigDecimal totalPrice) {
        return busDataService.payCar(custId,menuList,num,discount,totalPrice);
    }

    @RequestMapping("/car/orderList")
    public Object orderList(String name,String menuChiname,String menuEngname,String startTime,String endTime) {
        return busDataService.orderList(name,menuChiname,menuEngname,startTime,endTime);
    }

    @RequestMapping("/car/orderTotalList")
    public Object orderTotalList(String name,String menuChiname,String menuEngname,String startTime,String endTime) {
        return busDataService.orderTotalList(name,menuChiname,menuEngname,startTime,endTime);
    }




    @RequestMapping("/car/orderTxt")
    public void orderTxt(Integer orderId, HttpServletResponse response) throws IOException {
        busDataService.orderTxt(orderId,response);
    }




    @RequestMapping("/car/top10")
    public Object top10() {
        return busDataService.top10();
    }










}

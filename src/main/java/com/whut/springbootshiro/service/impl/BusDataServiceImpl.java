package com.whut.springbootshiro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.whut.springbootshiro.common.Constant;
import com.whut.springbootshiro.common.Result;
import com.whut.springbootshiro.form.BusCusForm;
import com.whut.springbootshiro.form.BusCusRegisterForm;
import com.whut.springbootshiro.form.BusMenuForm;
import com.whut.springbootshiro.mapper.*;
import com.whut.springbootshiro.model.*;
import com.whut.springbootshiro.query.BusCusQuery;
import com.whut.springbootshiro.query.BusMenuQuery;
import com.whut.springbootshiro.query.CarQuery;
import com.whut.springbootshiro.service.BusDataService;
import com.whut.springbootshiro.util.DateUtil;
import com.whut.springbootshiro.vo.CarVo;
import com.whut.springbootshiro.vo.MenuTopVo;
import com.whut.springbootshiro.vo.OrderListVo;
import com.whut.springbootshiro.vo.SysRoleVo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Lei
 * @create 2023-03-12 21:14
 */
@Service
public class BusDataServiceImpl implements BusDataService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleRelMapper sysUserRoleRelMapper;

    @Resource
    private CarMapper carMapper;


    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderMenuRelMapper orderMenuRelMapper;
    @Resource
    private PaymentMapper paymentMapper;


    @Override
    public Object selectMenuList(BusMenuQuery busMenuQuery) {
        QueryWrapper<Menu> busTagQueryWrapper = new QueryWrapper<>();
        busTagQueryWrapper.like(!Objects.isNull(busMenuQuery.getMenuChiname()), "menu_chiname", busMenuQuery.getMenuChiname())
                .like(!Objects.isNull(busMenuQuery.getMenuEngname()), "menu_engname", busMenuQuery.getMenuEngname());
        Page<Menu> menuPage = menuMapper.selectPage(new Page<>(busMenuQuery.getPage(), busMenuQuery.getLimit()), busTagQueryWrapper);
        return new Result(menuPage);
    }

    @Override
    public Object addMenu(BusMenuForm busMenuForm) {
        Menu menu = new Menu();
        BeanUtil.copyProperties(busMenuForm, menu, true);
        Integer maxIntId = menuMapper.getMaxIntId();
        if (maxIntId == null) {
            String key = "20" + String.format("%06d", 0);
            int nextInt = Integer.parseInt(key);
            menu.setMenuId(nextInt);
        } else {
            menu.setMenuId(maxIntId + 1);
        }
        menuMapper.insertSelective(menu);
        return new Result();
    }

    @Override
    public Object updateMenu(BusMenuForm busMenuForm) {
        Menu menu = new Menu();
        BeanUtil.copyProperties(busMenuForm, menu, true);
        menuMapper.updateByPrimaryKeySelective(menu);
        return new Result();
    }

    @Override
    public Object deleteMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
        return new Result();
    }

    @Override
    public Object selectCusList(BusCusQuery busCusQuery) {
        QueryWrapper<Customer> busTagQueryWrapper = new QueryWrapper<>();
        busTagQueryWrapper.like(!Objects.isNull(busCusQuery.getCustName()), "cust_name", busCusQuery.getCustName())
                .like(!Objects.isNull(busCusQuery.getCustSurName()), "cust_surname", busCusQuery.getCustSurName());
        Page<Customer> menuPage = customerMapper.selectPage(new Page<>(busCusQuery.getPage(), busCusQuery.getLimit()), busTagQueryWrapper);
        return new Result(menuPage);
    }

    @Override
    public Object addCus(BusCusForm busCusForm) {
        Customer customer = new Customer();
        BeanUtil.copyProperties(busCusForm, customer, true);
        customer.setCustZipcode(Integer.parseInt(busCusForm.getCustZipcode()));
        Integer maxIntId = customerMapper.getMaxIntId();
        if (maxIntId == null) {
            String key = "24" + String.format("%06d", 0);
            int nextInt = Integer.parseInt(key);
            customer.setCustId(nextInt);
        } else {
            customer.setCustId(maxIntId + 1);
        }
        customerMapper.insertSelective(customer);

        SysUser sysUser = new SysUser();
        Md5Hash md5Hash = new Md5Hash("123456", Constant.MD5_SALT, 2);
        sysUser.setLoginPassword(md5Hash.toString());
        sysUser.setLoginName(customer.getCustName());
        sysUser.setRealname(customer.getCustSurname());
        sysUser.setCreateTime(new Date());
        sysUser.setPhone(customer.getCustPhone());
        sysUser.setSex(1);
        sysUser.setAddress(customer.getCustAddress());
        sysUserMapper.insertSelective(sysUser);
        Integer id = sysUser.getId();
        sysUserRoleRelMapper.insert(new SysUserRoleRel(id, 6));
        return new Result();
    }

    @Override
    public Object updateCus(BusCusForm busCusForm) {
        Customer customer = new Customer();
        BeanUtil.copyProperties(busCusForm, customer, true);
        customer.setCustZipcode(Integer.parseInt(busCusForm.getCustZipcode()));
        customerMapper.updateByPrimaryKeySelective(customer);

        SysUser sysUser = sysUserMapper.selectByName(busCusForm.getCustName());
        sysUser.setRealname(customer.getCustSurname());
        sysUser.setPhone(customer.getCustPhone());
        sysUser.setAddress(customer.getCustAddress());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return new Result();
    }

    @Override
    public Object deleteCus(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        customerMapper.deleteByPrimaryKey(id);
        sysUserMapper.deleteByName(customer.getCustName());
        return new Result();
    }

    @Override
    public Object register(BusCusRegisterForm busCusRegisterForm) {
        Customer customer = new Customer();
        BeanUtil.copyProperties(busCusRegisterForm, customer, true);
        customer.setCustZipcode(Integer.parseInt(busCusRegisterForm.getCustZipcode()));
        Integer maxIntId = customerMapper.getMaxIntId();
        if (maxIntId == null) {
            String key = "24" + String.format("%06d", 0);
            int nextInt = Integer.parseInt(key);
            customer.setCustId(nextInt);
        } else {
            customer.setCustId(maxIntId + 1);
        }
        customerMapper.insertSelective(customer);

        SysUser sysUser = new SysUser();
        Md5Hash md5Hash = new Md5Hash(busCusRegisterForm.getCustPasswd(), Constant.MD5_SALT, 2);
        sysUser.setLoginPassword(md5Hash.toString());
        sysUser.setLoginName(customer.getCustName());
        sysUser.setRealname(customer.getCustSurname());
        sysUser.setCreateTime(new Date());
        sysUser.setPhone(customer.getCustPhone());
        sysUser.setSex(1);
        sysUser.setAddress(customer.getCustAddress());
        sysUserMapper.insertSelective(sysUser);
        Integer id = sysUser.getId();
        sysUserRoleRelMapper.insert(new SysUserRoleRel(id, 6));
        return new Result();
    }


    @Override
    public Object addCar(String name, int menuId, int num) {
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.eq("cust_name", name);
        Customer customer = customerMapper.selectOne(customerQueryWrapper);
        Integer custId = customer.getCustId();
        QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
        carQueryWrapper.eq("cust_id", custId).eq("menu_id", menuId);
        Car car1 = carMapper.selectOne(carQueryWrapper);
        if (car1 == null) {
            Car car = new Car(custId, menuId, num, new Date());
            carMapper.insertSelective(car);
        } else {
            car1.setNum(car1.getNum() + num);
            carMapper.updateByPrimaryKeySelective(car1);
        }
        return new Result();
    }


    @Override
    public Object delCar(Integer id) {
        carMapper.deleteByPrimaryKey(id);
        return new Result();
    }

    @Override
    public Object pageCar(CarQuery carQuery) {
        com.github.pagehelper.Page<CarVo> carVoPage = PageHelper.startPage(carQuery.getPage(), carQuery.getLimit());
        carMapper.selectList(carQuery);
        return new Result(carVoPage.toPageInfo());
    }


    @Override
    public Object payCar(int custId, int[] menuList, int[] num, int discount, BigDecimal totalPrice) {
        //插入一个order
        Order order = new Order();
        order.setCustId(custId);
        Integer maxIntId = orderMapper.getMaxIntId();
        if (maxIntId == null) {
            String key = "29" + String.format("%06d", 0);
            int nextInt = Integer.parseInt(key);
            order.setOrderId(nextInt);
        } else {
            order.setOrderId(maxIntId + 1);
        }
        order.setOrderDate(new Date());
        order.setOrderTime(new Date());
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(1);
        orderMapper.insertSelective(order);
        //插入关系
        for (int i = 0; i < menuList.length; i++) {
            OrderMenuRel orderMenuRel = new OrderMenuRel();
            orderMenuRel.setOrderId(order.getOrderId());
            orderMenuRel.setMenuId(menuList[i]);
            orderMenuRel.setNum(num[i]);
            orderMenuRelMapper.insertSelective(orderMenuRel);
        }

        Payment payment = new Payment();

        Integer payMax = paymentMapper.getMaxIntId();
        if (payMax == null) {
            String key = "29" + String.format("%06d", 0);
            int nextInt = Integer.parseInt(key);
            payment.setPaymentId(nextInt);
        } else {
            payment.setPaymentId(payMax + 1);
        }
        payment.setDiscount(discount);
        payment.setOrderId(order.getOrderId());
        payment.setParmentDate(new Date());
        payment.setGrandTotal(totalPrice.multiply(new BigDecimal((100-discount) / 100.0)));
        paymentMapper.insertSelective(payment);
        for (int i = 0; i < menuList.length; i++) {
            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
            carQueryWrapper.eq("cust_id", custId).eq("menu_id", menuList[i]);
            carMapper.delete(carQueryWrapper);
        }
        return new Result();
    }

    @Override
    public Object orderList(String name, String menuChiname, String menuEngname,String startTime,String endTime) {
        List<OrderListVo> orderListVos = orderMapper.selectCustOrderList(name, menuChiname, menuEngname,startTime,endTime);
        return new Result(orderListVos);
    }

    @Override
    public Object orderTotalList(String name, String menuChiname, String menuEngname, String startTime, String endTime) {
        BigDecimal orderListVos = orderMapper.selectCustTotalOrderList(name, menuChiname, menuEngname,startTime,endTime);
        return new Result(orderListVos);
    }

    @Override
    public Object top10() {
        List<MenuTopVo> top10 = orderMapper.top10();
        return new Result(top10);
    }

    @Override
    public void orderTxt(Integer orderId, HttpServletResponse response) throws IOException {
        // 设置响应头信息
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=test.txt");

        // 创建文件输出流
        OutputStream outputStream = response.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        List<OrderListVo> orderListVos = orderMapper.selectCustOrderListByOrderId(orderId);
        String title = "orderId\t" +
                "custId\t" +
                "custName\t" +
                "totalPrice\t" +
                "discount\t" +
                "grandTotal\t" +
                "parmentDate\t" +
                "menuId\t" +
                "menuChiname\t" +
                "menuEngname\t" +
                "description\t" +
                "num\t" +
                "export\n";
        // 写入文件内容
        writer.write(title);
        //编写内容，然后完成之后的txt文件
        for (int i = 0; i < orderListVos.size(); i++) {
            String data = "";
            data += orderListVos.get(i).getOrderId() + "\t";
            data += orderListVos.get(i).getCustId() + "\t";
            data += orderListVos.get(i).getCustName() + "\t";
            data += orderListVos.get(i).getTotalPrice() + "\t";
            data += orderListVos.get(i).getDiscount() + "\t";
            data += orderListVos.get(i).getGrandTotal() + "\t";
            data += DateUtil.getDateFormat(orderListVos.get(i).getParmentDate(), DateUtil.DATE_PATTERN_ALL) + "\t";
            data += orderListVos.get(i).getMenuChiname() + "\t";
            data += orderListVos.get(i).getMenuEngname() + "\t";
            data += orderListVos.get(i).getDescription() + "\t";
            data += orderListVos.get(i).getNum() + "\t";
            data += orderListVos.get(i).getPrice() + "\n";
            writer.write(data);
        }
        // 关闭流
        writer.flush();
        writer.close();
        outputStream.close();
    }
}

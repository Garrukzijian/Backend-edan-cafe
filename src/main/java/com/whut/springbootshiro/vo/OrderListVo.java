package com.whut.springbootshiro.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lei
 * @create 2023-03-15 0:39
 */
@Data
public class OrderListVo {
    private Integer orderId;
    private Integer custId;
    private BigDecimal totalPrice;
    private Integer num;
    private BigDecimal discount;

    private BigDecimal grandTotal;
    private String custName;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date parmentDate;
    private Integer menuId;
    private String menuChiname;

    private String menuEngname;

    private String description;

    private BigDecimal price;



}

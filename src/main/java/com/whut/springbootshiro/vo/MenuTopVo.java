package com.whut.springbootshiro.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Lei
 * @create 2023-03-21 19:13
 */
@Data
public class MenuTopVo {
    private Integer menuId;

    private String menuChiname;

    private String menuEngname;

    private String description;

    private BigDecimal sumMoney;


    private String picture;
}

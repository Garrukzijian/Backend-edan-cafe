package com.whut.springbootshiro.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lei
 * @create 2023-03-14 0:49
 */
@Data
public class CarVo {

    private  Integer id;
    private Integer custId;

    private String custName;

    private String custSurname;

    private Integer menuId;

    private String menuChiname;

    private String menuEngname;

    private String description;

    private BigDecimal price;

    private Integer num;

    private BigDecimal total;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public String picture;

}

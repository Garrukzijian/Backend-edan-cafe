package com.whut.springbootshiro.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Lei
 * @create 2023-03-12 21:26
 */
@Data
public class BusMenuForm {

    private Integer menuId;

    @NotEmpty(message = "中文名不可以为空")
    private String menuChiname;
    @NotEmpty(message = "英文名不可以为空")
    private String menuEngname;
    @NotEmpty(message = "描述不可以为空")
    private String description;
    @NotNull(message = "价格")
    private BigDecimal price;
    private String picture;
}

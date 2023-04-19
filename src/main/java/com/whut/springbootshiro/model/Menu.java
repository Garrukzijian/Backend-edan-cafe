package com.whut.springbootshiro.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
@TableName("menu")
public class Menu {
    @TableId
    private Integer menuId;

    private String menuChiname;

    private String menuEngname;

    private String description;

    private BigDecimal price;

    private String picture;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuChiname() {
        return menuChiname;
    }

    public void setMenuChiname(String menuChiname) {
        this.menuChiname = menuChiname == null ? null : menuChiname.trim();
    }

    public String getMenuEngname() {
        return menuEngname;
    }

    public void setMenuEngname(String menuEngname) {
        this.menuEngname = menuEngname == null ? null : menuEngname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }
}
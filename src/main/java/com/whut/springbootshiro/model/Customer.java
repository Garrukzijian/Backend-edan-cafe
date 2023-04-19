package com.whut.springbootshiro.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("customer")
public class Customer {
    @TableId
    private Integer custId;

    private String custName;

    private String custSurname;

    private String custDistirct;

    private String custAddress;

    private Integer custZipcode;

    private String custCity;

    private String custCountry;

    private String custPhone;

    private String custEmail;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCustSurname() {
        return custSurname;
    }

    public void setCustSurname(String custSurname) {
        this.custSurname = custSurname == null ? null : custSurname.trim();
    }

    public String getCustDistirct() {
        return custDistirct;
    }

    public void setCustDistirct(String custDistirct) {
        this.custDistirct = custDistirct == null ? null : custDistirct.trim();
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress == null ? null : custAddress.trim();
    }

    public Integer getCustZipcode() {
        return custZipcode;
    }

    public void setCustZipcode(Integer custZipcode) {
        this.custZipcode = custZipcode;
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity == null ? null : custCity.trim();
    }

    public String getCustCountry() {
        return custCountry;
    }

    public void setCustCountry(String custCountry) {
        this.custCountry = custCountry == null ? null : custCountry.trim();
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone == null ? null : custPhone.trim();
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail == null ? null : custEmail.trim();
    }
}
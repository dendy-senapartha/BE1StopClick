package com.Be1StopClick.dto;

import com.Be1StopClick.model.Invoice;

import java.math.BigDecimal;
import java.util.Date;
/*
 * Created by dendy-prtha on 01/07/2019.
 * payment method DTO
 */

public class PaymentMethodDTO {
    private int id;

    private String code;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

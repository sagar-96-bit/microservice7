package com.example.microservicedemo.order.res.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResp {
    private String orderId;
    private String orderName;
    private String quantity;
    private String price;
    private String empId;
}

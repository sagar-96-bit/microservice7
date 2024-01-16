package com.example.microservicedemo.order.req.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {

    private String orderName;
    private String quantity;
    private String price;
    private String empId;

}

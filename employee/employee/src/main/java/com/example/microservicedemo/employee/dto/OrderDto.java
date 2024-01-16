package com.example.microservicedemo.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderId;
    private String orderName;
    private String quantity;
    private String price;
    private String empId;
}

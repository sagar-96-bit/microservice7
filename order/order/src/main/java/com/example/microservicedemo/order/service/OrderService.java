package com.example.microservicedemo.order.service;

import com.example.microservicedemo.order.dto.OrderDto;

import java.util.List;

public interface OrderService {
    public OrderDto saveOrder(OrderDto orderDto);
    public  List<OrderDto> getOrder(String empId);
}

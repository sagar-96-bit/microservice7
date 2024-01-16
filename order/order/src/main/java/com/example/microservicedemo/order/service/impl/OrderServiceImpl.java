package com.example.microservicedemo.order.service.impl;

import com.example.microservicedemo.order.controller.Utils;
import com.example.microservicedemo.order.dto.OrderDto;
import com.example.microservicedemo.order.entity.Order;
import com.example.microservicedemo.order.repo.OrderRepo;
import com.example.microservicedemo.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    OrderRepo orderRepo;
    Utils utils;
    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        ModelMapper m=new ModelMapper();
        Order order=m.map(orderDto,Order.class);
        order.setOrderId(utils.generateRandomNumb(10));
        Order orderResult=orderRepo.save(order);
        OrderDto orderDtoResult=m.map(orderResult,OrderDto.class);
        return orderDtoResult;
    }
@Override
    public List<OrderDto> getOrder(String empId){
              List<Order> orderList=orderRepo.findByEmpId(empId);
              ModelMapper m=new ModelMapper();
        List<OrderDto>  orderDtoList= m.map(orderList, new TypeToken<List<OrderDto>>() {}.getType());
          return orderDtoList;
    }
}

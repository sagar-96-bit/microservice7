package com.example.microservicedemo.employee.controller;

import com.example.microservicedemo.employee.entity.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE")
public interface ApiCall {
    @GetMapping("/order/{empId}")
    public ResponseEntity<List<Order>> getOrder(@PathVariable String empId);

}

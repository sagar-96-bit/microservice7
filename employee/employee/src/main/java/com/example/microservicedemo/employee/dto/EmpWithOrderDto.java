package com.example.microservicedemo.employee.dto;

import com.example.microservicedemo.employee.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpWithOrderDto {
        EmpDto empDto;
        List<OrderDto> orders;
}

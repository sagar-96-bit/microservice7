package com.example.microservicedemo.order.repo;

import com.example.microservicedemo.order.dto.OrderDto;
import com.example.microservicedemo.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    public List<Order> findByEmpId(String empId);

}

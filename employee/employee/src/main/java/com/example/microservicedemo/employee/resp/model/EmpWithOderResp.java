package com.example.microservicedemo.employee.resp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpWithOderResp {
    EmpResp empResp;
    List<OrderResp> orderrespList;
}

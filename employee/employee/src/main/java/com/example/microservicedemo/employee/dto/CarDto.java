package com.example.microservicedemo.employee.dto;

import com.example.microservicedemo.employee.entity.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false,length = 20)
    private String carRegNo;
    @Column(nullable = false)
    private String carName;
    @Column(nullable = false)
    private String carModel;
    @Column(nullable = false)
    private String fuelType;
    private EmpDto employee;
}

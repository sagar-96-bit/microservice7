package com.example.microservicedemo.employee.dto;

import com.example.microservicedemo.employee.entity.Car;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpDto implements Serializable {
    private String empId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String mobileNo;

    private String password;

    private String passwordEncrypt;
    private List<CarDto> car;
}

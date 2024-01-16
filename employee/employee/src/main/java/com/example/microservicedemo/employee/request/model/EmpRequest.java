package com.example.microservicedemo.employee.request.model;

import com.example.microservicedemo.employee.entity.Car;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String mobileNo;
    @Column(nullable = false)
    private String password;
    private List<CarReq> car;
}

package com.example.microservicedemo.employee.resp.model;

import com.example.microservicedemo.employee.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpResp {
    private String empId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String mobileNo;
    private List<CarResp> car;

}

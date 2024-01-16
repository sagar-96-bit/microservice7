package com.example.microservicedemo.employee.resp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResp {
    private String carRegNo;
    private String carName;
    private String carModel;
}

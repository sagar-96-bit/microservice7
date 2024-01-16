package com.example.microservicedemo.employee.request.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarReq {

    private String carRegNo;
    private String carName;
    private String carModel;
    private String fuelType;
}

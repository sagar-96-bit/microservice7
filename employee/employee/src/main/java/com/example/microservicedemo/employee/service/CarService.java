package com.example.microservicedemo.employee.service;

import com.example.microservicedemo.employee.dto.CarDto;

import java.util.List;

public interface CarService {
    public List<CarDto> getAllCar(String userId);
    public CarDto getCar(String carId);
}

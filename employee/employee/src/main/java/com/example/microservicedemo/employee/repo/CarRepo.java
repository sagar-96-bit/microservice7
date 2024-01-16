package com.example.microservicedemo.employee.repo;

import com.example.microservicedemo.employee.dto.CarDto;
import com.example.microservicedemo.employee.entity.Car;
import com.example.microservicedemo.employee.entity.Employee;
import jakarta.persistence.Lob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepo extends JpaRepository<Car, Long> {
    public List<Car> findAllByEmployee(Employee employee);
    public Car findByCarRegNo(String carId);
}

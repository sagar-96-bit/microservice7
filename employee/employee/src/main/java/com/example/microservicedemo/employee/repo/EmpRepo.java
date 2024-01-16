package com.example.microservicedemo.employee.repo;

import com.example.microservicedemo.employee.dto.EmpDto;
import com.example.microservicedemo.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<Employee,Long> {
    public Employee findByEmpId(String id);
    public  Employee findByEmail(String username);
}

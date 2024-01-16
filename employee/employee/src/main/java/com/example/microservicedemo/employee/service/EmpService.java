package com.example.microservicedemo.employee.service;

import com.example.microservicedemo.employee.dto.EmpDto;
import com.example.microservicedemo.employee.dto.EmpWithOrderDto;
import com.example.microservicedemo.employee.request.model.EmpRequest;
import com.example.microservicedemo.employee.resp.model.EmpResp;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmpService extends UserDetailsService {
    public EmpDto createEmp(EmpDto empDto);
    public EmpDto getEmployee(String id);
    public List<EmpDto> getAllEmployee();
    public EmpDto updateEmployee(String id, EmpDto empDto);
    public  String deleteEmployee(String id);
    public List<EmpDto> getEmployeesWithPAging(int page,int limit);
    EmpDto getUser(String email);
    EmpWithOrderDto getEmployeeWithOrder(String empId);
}

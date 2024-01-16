package com.example.microservicedemo.employee.service.impl;


import com.example.microservicedemo.employee.controller.ApiCall;
import com.example.microservicedemo.employee.controller.Utils;
import com.example.microservicedemo.employee.dto.CarDto;
import com.example.microservicedemo.employee.dto.EmpDto;
import com.example.microservicedemo.employee.dto.EmpWithOrderDto;
import com.example.microservicedemo.employee.dto.OrderDto;
import com.example.microservicedemo.employee.entity.Employee;
import com.example.microservicedemo.employee.entity.Order;
import com.example.microservicedemo.employee.repo.EmpRepo;

import com.example.microservicedemo.employee.service.EmpService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class EmpServiceImpl implements EmpService {
    EmpRepo empRepo;
    Utils utils;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    ApiCall apiCall;


    @Override
    public EmpDto createEmp(EmpDto empDto) {
        ModelMapper m=new ModelMapper();
        for(int i=0;i<empDto.getCar().size();i++){
            CarDto carDto=empDto.getCar().get(i);
            carDto.setEmployee(empDto);
        }
        Employee employee=m.map(empDto, Employee.class);
        employee.setEmpId(utils.generateRandomNumb(10));
         employee.setPasswordEncrypt(bCryptPasswordEncoder.encode(empDto.getPassword()));
        Employee employeeRes=empRepo.save(employee);
        EmpDto empDtoRes=m.map(employeeRes, EmpDto.class);
        return empDtoRes;
    }

    @Transactional
    @Override
    public EmpDto getEmployee(String id) {
        ModelMapper m=new ModelMapper();

        Employee employee=empRepo.findByEmpId(id);
        EmpDto empDto =m.map(employee, EmpDto.class);
        return empDto;
    }

    @Override
    public List<EmpDto> getAllEmployee() {
        Type listType = new TypeToken<List<EmpDto>>() {}.getType();
        ModelMapper m=new ModelMapper();

        List<Employee> employeeList=empRepo.findAll();
        List<EmpDto> empDtoList = m.map(employeeList, listType);

        return empDtoList;
    }

    @Override
    public EmpDto updateEmployee(String id, EmpDto empDto) {
        ModelMapper m=new ModelMapper();
        Employee employee=empRepo.findByEmpId(id);
           if(employee ==null){
               throw new RuntimeException("user not found");
           }
            employee.setEmail(empDto.getEmail());
           employee.setAddress(empDto.getAddress());
           employee.setLastName(empDto.getLastName());
           employee.setFirstName(empDto.getFirstName());
           employee.setMobileNo(empDto.getMobileNo());
           Employee employeeStored=empRepo.save(employee);
            EmpDto empDtoResult=m.map(employeeStored, EmpDto.class);
           return empDtoResult;
    }

    @Override
    public String deleteEmployee(String id) {
          Employee employee=empRepo.findByEmpId(id);
            if(employee == null) throw new RuntimeException("User not found");
        empRepo.delete(employee);
        return "record deleted successfully";
    }

    @Override
    public List<EmpDto> getEmployeesWithPAging(int page, int limit) {
        ModelMapper m=new ModelMapper();
        Type listType = new TypeToken<List<EmpDto>>() {}.getType();

        Pageable pageRequest=PageRequest.of(page,limit);
        Page<Employee> page1=empRepo.findAll(pageRequest);
        List<Employee>  employeeList=page1.getContent();
        List<EmpDto> empDto=m.map(employeeList,listType);
        return empDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee=empRepo.findByEmail(username);
         if(employee == null) throw new UsernameNotFoundException("use not found");
        return new User(username,employee.getPasswordEncrypt(),new ArrayList<>());
    }
@Transactional
    public EmpDto getUser(String email){
        ModelMapper m=new ModelMapper();
        Employee employee= empRepo.findByEmail(email);
       EmpDto empDto=m.map(employee, EmpDto.class);
       return empDto;
    };
@Transactional
    public EmpWithOrderDto getEmployeeWithOrder(String empId){
        ModelMapper m=new ModelMapper();
                  Employee employee=empRepo.findByEmpId(empId);
                  if(employee == null) throw new UsernameNotFoundException("User Not found");
        List<Order> order= apiCall.getOrder(employee.getEmpId()).getBody();
        Type listType = new TypeToken<List<OrderDto>>() {}.getType();
        List<OrderDto> orderDtoResult=m.map(order,listType);
            EmpDto empDtoResult=m.map(employee, EmpDto.class);
            EmpWithOrderDto empWithOrderDto=new EmpWithOrderDto();
            empWithOrderDto.setEmpDto(empDtoResult);
            empWithOrderDto.setOrders(orderDtoResult);
            return empWithOrderDto;

    }


}

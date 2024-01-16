package com.example.microservicedemo.employee.service.impl;

import com.example.microservicedemo.employee.dto.CarDto;
import com.example.microservicedemo.employee.dto.EmpDto;
import com.example.microservicedemo.employee.entity.Car;
import com.example.microservicedemo.employee.entity.Employee;
import com.example.microservicedemo.employee.repo.CarRepo;
import com.example.microservicedemo.employee.repo.EmpRepo;
import com.example.microservicedemo.employee.resp.model.CarResp;
import com.example.microservicedemo.employee.service.CarService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
import java.util.List;
@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
     CarRepo carRepo;
     EmpRepo empRepo;
    @Override
    public List<CarDto> getAllCar(String userId) {

       Employee employee= empRepo.findByEmpId(userId);
       if(employee ==null) throw  new RuntimeException("No Records");
           ModelMapper m=new ModelMapper();
           Type listType = new TypeToken<List<CarDto>>() {}.getType();
              List<Car> cars=carRepo.findAllByEmployee(employee);
           List<CarDto> carRespList = m.map(cars, listType);


        return carRespList;
    }

    @Override
    public CarDto getCar(String carId) {
        ModelMapper m=new ModelMapper();
          Car car=carRepo.findByCarRegNo(carId);
          if(car == null ) throw new RuntimeException("car Not Available");
          CarDto carDto=m.map(car,CarDto.class);
          return carDto;
    }


}

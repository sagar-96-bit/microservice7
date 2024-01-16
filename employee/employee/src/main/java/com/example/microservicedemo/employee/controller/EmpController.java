package com.example.microservicedemo.employee.controller;

import com.example.microservicedemo.employee.dto.CarDto;
import com.example.microservicedemo.employee.dto.EmpDto;
import com.example.microservicedemo.employee.dto.EmpWithOrderDto;
import com.example.microservicedemo.employee.dto.OrderDto;
import com.example.microservicedemo.employee.request.model.EmpRequest;
import com.example.microservicedemo.employee.resp.model.CarResp;
import com.example.microservicedemo.employee.resp.model.EmpResp;
import com.example.microservicedemo.employee.resp.model.EmpWithOderResp;
import com.example.microservicedemo.employee.resp.model.OrderResp;
import com.example.microservicedemo.employee.service.CarService;
import com.example.microservicedemo.employee.service.EmpService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("employee")
@AllArgsConstructor
public class EmpController {
    EmpService empService;
    CarService carService;
    //Logger logger=LoggerFactory.getLogger(EmpController.class);


    @PostMapping("/register")
    public ResponseEntity<EmpResp> createEmp(@RequestBody EmpRequest empRequest){
        Logger logger=LoggerFactory.getLogger(EmpController.class);
        logger.info("inside emp class create method");
    ModelMapper m=new ModelMapper();
    EmpDto empDto=  m.map(empRequest, EmpDto.class);
         EmpDto empDtoRes= empService.createEmp(empDto);
          EmpResp empRespRes =  m.map(empDtoRes,EmpResp.class);
          return  new ResponseEntity<>(empRespRes, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpResp>getEmployee(@PathVariable String id){
        Logger logger=LoggerFactory.getLogger(EmpController.class);
        logger.info("inside emp class get method");
        ModelMapper m=new ModelMapper();
    EmpDto empDtoRes=empService.getEmployee(id);
          EmpResp empResp=m.map(empDtoRes, EmpResp.class);
          return new ResponseEntity<>(empResp,HttpStatus.OK);
    }
//@GetMapping
//    public ResponseEntity<List<EmpResp>> getAllEmployee(){
//        Type listType = new TypeToken<List<EmpResp>>() {}.getType();
//        ModelMapper m=new ModelMapper();
//        List<EmpDto> empDtoList=empService.getAllEmployee();
//         List<EmpResp> empRespList=m.map(empDtoList,listType);
//         return new ResponseEntity<>(empRespList, HttpStatus.OK);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<EmpResp> updateEmployee(@PathVariable String id,@RequestBody EmpRequest empRequest){
    ModelMapper m=new ModelMapper();
    EmpDto empDto =m.map(empRequest, EmpDto.class);
    EmpDto empDtoResult=empService.updateEmployee(id,empDto);
   EmpResp empResp= m.map(empDtoResult, EmpResp.class);
   return new ResponseEntity<>(empResp,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id){
           String result=empService.deleteEmployee(id);
           return new ResponseEntity<>(result,HttpStatus.OK);
    }
     @GetMapping("/{id}/car")
    public ResponseEntity<List<CarResp>> getAllCar(@PathVariable  String id){
         List<CarDto> listCarDto=carService.getAllCar(id);
         ModelMapper m=new ModelMapper();
         Type listType = new TypeToken<List<CarResp>>() {}.getType();
         List<CarResp> carRespList = m.map(listCarDto, listType);
         return new ResponseEntity<>(carRespList,HttpStatus.OK);
    }
       @GetMapping("/{empId}/car/{carId}")
      public ResponseEntity<CarResp> getCar(@PathVariable String carId){
           ModelMapper m=new ModelMapper();
             CarDto carDto=carService.getCar(carId);
             CarResp carResp=m.map(carDto, CarResp.class);
             return new ResponseEntity<>(carResp,HttpStatus.OK);
}
  @GetMapping
public ResponseEntity<List<EmpResp>> getEmployeeWithPaging(@RequestParam(value = "page",defaultValue = "0") int page,@RequestParam(value = "limit",defaultValue = "2") int limit){
      ModelMapper m=new ModelMapper();
      Type listType = new TypeToken<List<EmpResp>>() {}.getType();

        List<EmpDto> empDtoList =empService.getEmployeesWithPAging(page,limit);
        List<EmpResp> empRespList=m.map(empDtoList,listType);
           return new ResponseEntity<>(empRespList,HttpStatus.OK);
}
@GetMapping("/empwithorder/{id}")
public ResponseEntity<EmpWithOderResp> getEmployeeWithOrder(@PathVariable String id){
    Logger logger=LoggerFactory.getLogger(EmpController.class);

    logger.info("inside emp getEmporder method");
    ModelMapper m=new ModelMapper();
    Type listType = new TypeToken<List<OrderResp>>() {}.getType();

    EmpWithOrderDto empWithPrderDto=empService.getEmployeeWithOrder(id);
           EmpWithOderResp empWithOderResp=new EmpWithOderResp();
             empWithOderResp.setEmpResp(m.map(empWithPrderDto.getEmpDto(),EmpResp.class));
               empWithOderResp.setOrderrespList(m.map(empWithPrderDto.getOrders(),listType));
          return new ResponseEntity<EmpWithOderResp>(empWithOderResp,HttpStatus.OK);

}
}

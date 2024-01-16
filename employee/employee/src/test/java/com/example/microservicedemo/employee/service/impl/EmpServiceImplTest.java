package com.example.microservicedemo.employee.service.impl;

import com.example.microservicedemo.employee.dto.CarDto;
import com.example.microservicedemo.employee.dto.EmpDto;
import com.example.microservicedemo.employee.entity.Employee;
import com.example.microservicedemo.employee.repo.EmpRepo;
import com.example.microservicedemo.employee.service.EmpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@Transactional
class EmpServiceImplTest {
    @Mock
      EmpRepo empRepo;
    @InjectMocks
    EmpServiceImpl empService;
    Employee e;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        e=new Employee();
        e.setEmpId("e123");
        e.setFirstName("joy");
        e.setLastName("nadia");
        e.setAddress("pune");
        e.setMobileNo("987");
        e.setEmail("joy@gmail.com");

    }

    @Test
    void getEmployee() {
        //Employee e=new Employee();
        when(empRepo.findByEmpId(anyString())).thenReturn(e);
      EmpDto empDto=empService.getEmployee("a123");
      assertNotNull(empDto);
      assertEquals("joy",empDto.getFirstName());
    }

    @Test
    final void getEmployeeExceptionHandling(){
        when(empRepo.findByEmpId(anyString())).thenReturn(null);
          assertThrows(RuntimeException.class,()->{
              empService.getEmployee("a123");

          });
    }

    @Test
    final void createEmployeeTest() {
           when(empRepo.findByEmpId(anyString())).thenReturn(null);
           when(empRepo.save(ArgumentMatchers.any(Employee.class))).thenReturn(e);
              EmpDto empDto=new EmpDto();
        CarDto  carDto=new CarDto();
        carDto.setCarModel("M1");
        List<CarDto> carDtoList=new ArrayList<CarDto>();
          carDtoList.add(carDto);

              //empDto.setEmpId("w1234");
              empDto.setEmail("sp@gmail.com");
              empDto.setFirstName("sagar");
              empDto.setLastName("patel");
              empDto.setMobileNo("9687");
              empDto.setCar(carDtoList);
            EmpDto empDto1=empService.createEmp(empDto);
            assertEquals(e.getFirstName(),empDto1.getFirstName());
     }
}
package com.example.microservicedemo.order.controller;

import com.example.microservicedemo.order.dto.OrderDto;
import com.example.microservicedemo.order.req.model.OrderReq;
import com.example.microservicedemo.order.res.model.OrderResp;
import com.example.microservicedemo.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    OrderService orderService;
@PostMapping
    public ResponseEntity<OrderResp> saveOrder(@RequestBody OrderReq orderReq){
    ModelMapper m=new ModelMapper();

    OrderDto orderDto=m.map(orderReq, OrderDto.class);
      OrderDto orderDtoResult=orderService.saveOrder(orderDto);
      OrderResp orderResp=m.map(orderDtoResult, OrderResp.class);
      return new ResponseEntity<>(orderResp, HttpStatus.CREATED);

}
@GetMapping("/{empId}")
public ResponseEntity<List<OrderResp>> getOrder(@PathVariable String empId){
    ModelMapper m=new ModelMapper();

    List<OrderDto> orderDtoList=orderService.getOrder(empId);
    List<OrderResp>  orderRespList= m.map(orderDtoList, new TypeToken<List<OrderResp>>() {}.getType());

    return new ResponseEntity<>(orderRespList,HttpStatus.OK);
}


}

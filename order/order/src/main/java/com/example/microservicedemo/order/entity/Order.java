package com.example.microservicedemo.order.entity;



import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Table(name = "myorder")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderId;
    private String orderName;
    private String quantity;
    private String price;
    private String empId;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
}

package com.example.microservicedemo.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false,length = 20)
    private String carRegNo;
    @Column(nullable = false)
    private String carName;
    @Column(nullable = false)
    private String carModel;
    @Column(nullable = false)
    private String fuelType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="empdata_id")
    private Employee employee;

}

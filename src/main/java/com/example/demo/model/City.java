package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private Integer id;
   
    private String name;
    
    private Integer zipCode;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private Estate state;




}

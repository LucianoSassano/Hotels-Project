package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "beddings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bedding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bedding_id")
    private Long id;
    private String description;
    private int maxCapacity;


}
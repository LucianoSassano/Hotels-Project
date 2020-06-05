package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "hotel_id")
    private Long id;
    private String name;
    private String address;
//    @ManyToOne
//    @JoinColumn(name = city_id)
//    private City city;
    private String email;
    private Long phone;
    private Boolean status;
    private Integer roomCapacity;
    private Integer rating;
    @OneToMany
    @JoinColumn(name="hotel_id")
    private List<Room> rooms;

}

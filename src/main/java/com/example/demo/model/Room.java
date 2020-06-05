package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @JoinColumn(name= "bedding_id")
    private Bedding bedding;
    private Boolean status;
    private Double dailyRate;
}

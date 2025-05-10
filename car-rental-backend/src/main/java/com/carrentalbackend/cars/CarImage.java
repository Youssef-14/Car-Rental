package com.carrentalbackend.cars;

import jakarta.persistence.*;

public class CarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}

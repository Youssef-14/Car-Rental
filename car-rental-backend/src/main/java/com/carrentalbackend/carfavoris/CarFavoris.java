package com.carrentalbackend.carfavoris;

import com.carrentalbackend.carfavoris.dto.GetCarFavorisDto;
import com.carrentalbackend.cars.Car;
import com.carrentalbackend.users.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity
public class CarFavoris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    // Constructeurs, getters, setters

    public CarFavoris() {}

    public CarFavoris(Long id, User user, Car car, Date createdAt) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.createdAt = createdAt;
    }

    // convert to DTO
    public GetCarFavorisDto toCarFavorisDto() {
        return new GetCarFavorisDto(id, car.getCarDto());
    }

    public CarFavoris(User user, Car car) {
        this.user = user;
        this.car = car;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarFavoris)) return false;
        CarFavoris that = (CarFavoris) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CarFavoris{" +
                "id=" + id +
                ", user=" + user +
                ", car=" + car +
                ", createdAt=" + createdAt +
                '}';
    }
}

package com.carrentalbackend.carfavoris.dto;

import com.carrentalbackend.cars.CarDto;
import com.carrentalbackend.users.dto.UserDto;

public class GetCarFavorisDto {
    private Long id;
    private CarDto car;

    public GetCarFavorisDto() {
    }

    public GetCarFavorisDto(Long id, CarDto car) {
        this.id = id;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "GetCarFavorisDto{" +
                "id=" + id +
                ", car=" + car +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetCarFavorisDto)) return false;
        GetCarFavorisDto that = (GetCarFavorisDto) o;
        return id.equals(that.id) && car.equals(that.car);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + car.hashCode();
        return result;
    }

    public static GetCarFavorisDto fromCarFavorisDto(UserDto user, CarDto car) {
        return new GetCarFavorisDto(user.getId(), car);
    }

    public static GetCarFavorisDto fromCarFavorisDto(Long id, CarDto car) {
        return new GetCarFavorisDto(id, car);
    }

    public static GetCarFavorisDto fromCarFavorisDto(Long id, UserDto user, CarDto car) {
        return new GetCarFavorisDto(id, car);
    }
}

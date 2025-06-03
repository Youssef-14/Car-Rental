package com.carrentalbackend.carfavoris;

public class AddCarFavorisDto {
    private Long userId;
    private Long carId;

    public AddCarFavorisDto() {
    }

    public AddCarFavorisDto(Long userId, Long carId) {
        this.userId = userId;
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "AddCarFavorisDto{" +
                "userId=" + userId +
                ", carId=" + carId +
                '}';
    }
}

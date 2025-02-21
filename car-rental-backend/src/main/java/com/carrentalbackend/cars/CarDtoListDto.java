package com.carrentalbackend.cars;

import java.util.List;
import java.util.Objects;

public class CarDtoListDto {
    private List<CarDto> carDtoList;

    public CarDtoListDto() {
    }

    public List<CarDto> getCarDtoList() {
        return this.carDtoList;
    }

    public void setCarDtoList(List<CarDto> carDtoList) {
        this.carDtoList = carDtoList;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CarDtoListDto other)) return false;
        if (!other.canEqual((Object) this)) return false;
        final Object this$carDtoList = this.getCarDtoList();
        final Object other$carDtoList = other.getCarDtoList();
        return Objects.equals(this$carDtoList, other$carDtoList);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CarDtoListDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $carDtoList = this.getCarDtoList();
        result = result * PRIME + ($carDtoList == null ? 43 : $carDtoList.hashCode());
        return result;
    }

    public String toString() {
        return "CarDtoListDto(carDtoList=" + this.getCarDtoList() + ")";
    }
}

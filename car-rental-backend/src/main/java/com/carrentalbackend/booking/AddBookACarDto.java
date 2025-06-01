package com.carrentalbackend.booking;

import com.carrentalbackend.cars.Car;
import com.carrentalbackend.cars.CarDto;
import com.carrentalbackend.users.dto.UserDto;

import java.util.Date;

public class AddBookACarDto {
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Long price;
    private Long carId;
    private Long userId;
    private Date reservationDate;

    public AddBookACarDto() {
    }

    public Date getFromDate() {
        return this.fromDate;
    }

    public Date getToDate() {
        return this.toDate;
    }

    public Long getDays() {
        return this.days;
    }

    public Long getPrice() {
        return this.price;
    }

    public Long getCarId() {
        return this.carId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Date getReservationDate() {
        return this.reservationDate;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BookACarDto)) return false;
        final BookACarDto other = (BookACarDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$fromDate = this.getFromDate();
        final Object other$fromDate = other.getFromDate();
        if (this$fromDate == null ? other$fromDate != null : !this$fromDate.equals(other$fromDate)) return false;
        final Object this$toDate = this.getToDate();
        final Object other$toDate = other.getToDate();
        if (this$toDate == null ? other$toDate != null : !this$toDate.equals(other$toDate)) return false;
        final Object this$days = this.getDays();
        final Object other$days = other.getDays();
        if (this$days == null ? other$days != null : !this$days.equals(other$days)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BookACarDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $fromDate = this.getFromDate();
        result = result * PRIME + ($fromDate == null ? 43 : $fromDate.hashCode());
        final Object $toDate = this.getToDate();
        result = result * PRIME + ($toDate == null ? 43 : $toDate.hashCode());
        final Object $days = this.getDays();
        result = result * PRIME + ($days == null ? 43 : $days.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        return result;
    }

    public String toString() {
        return "BookACarDto(id=" + ", fromDate=" + this.getFromDate() + ", toDate=" + this.getToDate() + ", days=" + this.getDays() + ", price=" + this.getPrice() +  ", carId=" + this.getCarId() + ", userId=" + this.getUserId() + ")";
    }
}

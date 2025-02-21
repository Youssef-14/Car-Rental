package com.carrentalbackend.booking;

import com.carrentalbackend.cars.Car;
import com.carrentalbackend.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
public class BookACar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Long price;
    private BookCarStatus bookCarStatus;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore // @JsonIgnore is used to ignore the user field when serializing the object to JSON.
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;

    public BookACar() {
    }

    public BookACarDto getBookACarDto() {
        BookACarDto bookACarDto = new BookACarDto();
        bookACarDto.setId(this.id);
        bookACarDto.setDays(this.days);
        bookACarDto.setBookCarStatus(this.bookCarStatus);
        bookACarDto.setPrice(this.price);
        bookACarDto.setToDate(this.toDate);
        bookACarDto.setFromDate(this.fromDate);
        bookACarDto.setEmail(this.user.getEmail());
        bookACarDto.setUsername(this.user.getName());
        bookACarDto.setUserId(this.user.getId());
        bookACarDto.setCarId(this.car.getId());
        return bookACarDto;
    }

    public Long getId() {
        return this.id;
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

    public BookCarStatus getBookCarStatus() {
        return this.bookCarStatus;
    }

    public User getUser() {
        return this.user;
    }

    public Car getCar() {
        return this.car;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setBookCarStatus(BookCarStatus bookCarStatus) {
        this.bookCarStatus = bookCarStatus;
    }

    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public void setCar(Car car) {
        this.car = car;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BookACar)) return false;
        final BookACar other = (BookACar) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
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
        final Object this$bookCarStatus = this.getBookCarStatus();
        final Object other$bookCarStatus = other.getBookCarStatus();
        if (this$bookCarStatus == null ? other$bookCarStatus != null : !this$bookCarStatus.equals(other$bookCarStatus))
            return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final Object this$car = this.getCar();
        final Object other$car = other.getCar();
        if (this$car == null ? other$car != null : !this$car.equals(other$car)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BookACar;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $fromDate = this.getFromDate();
        result = result * PRIME + ($fromDate == null ? 43 : $fromDate.hashCode());
        final Object $toDate = this.getToDate();
        result = result * PRIME + ($toDate == null ? 43 : $toDate.hashCode());
        final Object $days = this.getDays();
        result = result * PRIME + ($days == null ? 43 : $days.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $bookCarStatus = this.getBookCarStatus();
        result = result * PRIME + ($bookCarStatus == null ? 43 : $bookCarStatus.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final Object $car = this.getCar();
        result = result * PRIME + ($car == null ? 43 : $car.hashCode());
        return result;
    }

    public String toString() {
        return "BookACar(id=" + this.getId() + ", fromDate=" + this.getFromDate() + ", toDate=" + this.getToDate() + ", days=" + this.getDays() + ", price=" + this.getPrice() + ", bookCarStatus=" + this.getBookCarStatus() + ", user=" + this.getUser() + ", car=" + this.getCar() + ")";
    }
}

package com.carrentalbackend.booking;

import java.util.Date;

public class BookACarDto {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Long price;
    private BookCarStatus bookCarStatus;
    private Long carId;
    private Long userId;
    private String username;
    private String email;

    public BookACarDto() {
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

    public Long getCarId() {
        return this.carId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
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

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BookACarDto)) return false;
        final BookACarDto other = (BookACarDto) o;
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
        final Object this$carId = this.getCarId();
        final Object other$carId = other.getCarId();
        if (this$carId == null ? other$carId != null : !this$carId.equals(other$carId)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BookACarDto;
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
        final Object $carId = this.getCarId();
        result = result * PRIME + ($carId == null ? 43 : $carId.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "BookACarDto(id=" + this.getId() + ", fromDate=" + this.getFromDate() + ", toDate=" + this.getToDate() + ", days=" + this.getDays() + ", price=" + this.getPrice() + ", bookCarStatus=" + this.getBookCarStatus() + ", carId=" + this.getCarId() + ", userId=" + this.getUserId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ")";
    }
}

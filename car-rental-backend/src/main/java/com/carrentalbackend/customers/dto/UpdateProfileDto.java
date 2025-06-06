package com.carrentalbackend.customers.dto;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

public class UpdateProfileDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String licenseNumber;
    private String address;
    // optional field for license image
    @Column(nullable = true)
    private MultipartFile licenseImage;

    public UpdateProfileDto() {
    }

    public UpdateProfileDto(Long id, String firstName, String lastName, String phoneNumber, String licenseNumber, String address, MultipartFile licenseImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.address = address;
        this.licenseImage = licenseImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(MultipartFile licenseImage) {
        this.licenseImage = licenseImage;
    }

    @Override
    public String toString() {
        return "UpdateProfileDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateProfileDto)) return false;

        UpdateProfileDto that = (UpdateProfileDto) o;

        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!phoneNumber.equals(that.phoneNumber)) return false;
        if (!licenseNumber.equals(that.licenseNumber)) return false;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + licenseNumber.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

}

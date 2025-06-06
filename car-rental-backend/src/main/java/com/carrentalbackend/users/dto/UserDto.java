package com.carrentalbackend.users.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String number;
    private Date createdAt;
    private Date updatedAt;
    private String licenseNumber;
    private String address;
    private byte[] licenseImage;

    public UserDto() {
    }

    public UserDto(Long id, String firstname, String lastname, String email, String number, Date createdAt, Date updatedAt, String licenseNumber, String address, byte[] licenseImage) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
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

    public byte[] getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(byte[] licenseImage) {
        this.licenseImage = licenseImage;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}

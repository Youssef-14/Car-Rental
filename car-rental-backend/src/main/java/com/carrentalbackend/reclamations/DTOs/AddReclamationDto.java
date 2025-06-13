package com.carrentalbackend.reclamations.DTOs;

public class AddReclamationDto {
    private Long bookACarId;
    private String description;

    public AddReclamationDto() {
    }

    public AddReclamationDto(Long bookACarId, String description) {
        this.bookACarId = bookACarId;
        this.description = description;
    }

    public Long getBookACarId() {
        return bookACarId;
    }

    public void setBookACarId(Long bookACarId) {
        this.bookACarId = bookACarId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AddReclamationDto{" +
                "bookACarId=" + bookACarId +
                ", description='" + description + '\'' +
                '}';
    }

}

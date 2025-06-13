package com.carrentalbackend.reclamations.DTOs;

import com.carrentalbackend.booking.BookACarDto;

import java.util.Date;

public class GetReclamationDto {
    private Long id;
    private String description;
    private BookACarDto bookACarDto;
    private Date reclamationDate;

    public GetReclamationDto() {
    }

    public GetReclamationDto(Long id, String description, BookACarDto bookACarDto, Date reclamationDate) {
        this.id = id;
        this.description = description;
        this.bookACarDto = bookACarDto;
        this.reclamationDate = reclamationDate; // Convert Date to String
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookACarDto getBookACarDto() {
        return bookACarDto;
    }

    public void setBookACarDto(BookACarDto bookACarDto) {
        this.bookACarDto = bookACarDto;
    }

    public Date getReclamationDate() {
        return reclamationDate;
    }

    public void setReclamationDate(Date reclamationDate) {
        this.reclamationDate = reclamationDate;
    }
}

package com.carrentalbackend.customers;


import com.carrentalbackend.booking.BookACarDto;
import com.carrentalbackend.cars.CarDto;

import java.util.List;

public interface CustomerService {
    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto);

    CarDto getCarById(Long id);

    List<BookACarDto> getBookingsByUserId(Long id);
}

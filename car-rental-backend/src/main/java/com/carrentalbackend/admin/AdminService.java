package com.carrentalbackend.admin;


import com.carrentalbackend.booking.BookACarDto;
import com.carrentalbackend.cars.CarDto;
import com.carrentalbackend.cars.CarDtoListDto;
import com.carrentalbackend.cars.SearchCarDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    void deleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long id, CarDto carDto) throws IOException;

    List<BookACarDto> getBookings();


    boolean changeBookingStatus(Long id, String status);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);
}

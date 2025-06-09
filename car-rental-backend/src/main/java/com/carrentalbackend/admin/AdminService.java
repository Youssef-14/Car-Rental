package com.carrentalbackend.admin;


import com.carrentalbackend.booking.BookACarDto;
import com.carrentalbackend.cars.CarDto;
import com.carrentalbackend.cars.dto.CarDtoListDto;
import com.carrentalbackend.cars.dto.SearchCarDto;
import com.carrentalbackend.users.dto.UserDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    List<UserDto> getAllUsers();

    int getTotalCustomersCount();

    void deleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long id, CarDto carDto) throws IOException;

    List<BookACarDto> getBookings();

    List<BookACarDto> getBookingsByUserId(Long userId);

    List<BookACarDto> getBookingsByCarId(Long carId);

    List<BookACarDto> getBookingsByStatus(String status);

    BookACarDto getBookingById(Long id);

    int getTotalBookingsCount();

    int getTotalBookingsCountByUserId(Long userId);

    int getTotalBookingsCountByCarId(Long carId);

    int getTotalBookingsCountByStatus(String status);


    boolean changeBookingStatus(Long id, String status);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);

    void hideShowCar(Long id) throws IOException;

    int getTotalCarsCount();

    int getTotalBookingsCountThisMonth();

    int getTotalRevenue();

    int getTotalRevenueThisMonth();

    int getTotalRevenueThisWeek();
}

package com.carrentalbackend.customers;


import com.carrentalbackend.booking.AddBookACarDto;
import com.carrentalbackend.booking.BookACarDto;
import com.carrentalbackend.carfavoris.AddCarFavorisDto;
import com.carrentalbackend.cars.CarDto;
import com.carrentalbackend.customers.dto.UpdateProfileDto;
import com.carrentalbackend.users.dto.UserDto;
import jakarta.mail.MessagingException;

import java.util.List;

public interface CustomerService {
    List<CarDto> getAllCars();

    boolean bookACar(AddBookACarDto bookACarDto);

    boolean updateProfile(UpdateProfileDto updateProfileDto);

    UserDto getProfile(Long userId);

    CarDto getCarById(Long id);

    List<BookACarDto> getBookingsByUserId(Long id);

    int getBookingsCountByUserIdAndStatus(Long userId, String status);

    List<BookACarDto> getCarDisponibility(Long carId);


    List<CarDto> getCarFavorisByUserId(Long userId);

    void addCarToFavoris(AddCarFavorisDto addCarFavorisDto);

    int getTotalCarAvailableCount();

    boolean cancelBooking(Long bookingId);

    boolean generateVerificationToken(Long userId) throws MessagingException;

    boolean verifyUserActivationToken(Long userId, String token);

    /*int getCarFavorisCount(Long userId);

    void removeCarFromFavoris(Long userId, Long carId);*/

}

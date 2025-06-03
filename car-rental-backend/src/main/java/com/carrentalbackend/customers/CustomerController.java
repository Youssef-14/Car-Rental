package com.carrentalbackend.customers;

import com.carrentalbackend.booking.AddBookACarDto;
import com.carrentalbackend.booking.BookACarDto;
import com.carrentalbackend.carfavoris.AddCarFavorisDto;
import com.carrentalbackend.cars.CarDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(customerService.getAllCars());
    }

    @PostMapping("/car/book")
    public ResponseEntity<Void> bookACar(@RequestBody AddBookACarDto bookACarDto) {
        boolean isSuccessful = customerService.bookACar(bookACarDto);

        if (isSuccessful) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId) {
        CarDto carDto = customerService.getCarById(carId);

        if (carDto != null) {
            return ResponseEntity.ok(carDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/car/bookings/{userId}")
    public ResponseEntity<List<BookACarDto>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }

    @GetMapping("/car/bookings/count/{userId}/{status}")
    public ResponseEntity<Integer> getBookingsCountByUserIdAndStatus(@PathVariable Long userId, @PathVariable String status) {
        int count = customerService.getBookingsCountByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/car/car_disponibility/{carId}")
    public ResponseEntity<List<BookACarDto>> getCarDisponibility(@PathVariable Long carId) {
        return ResponseEntity.ok(customerService.getCarDisponibility(carId));
    }

    @PostMapping("/car/favoris")
    public ResponseEntity<Void> addCarToFavoris(@RequestBody AddCarFavorisDto addCarFavorisDto) {
        customerService.addCarToFavoris(addCarFavorisDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/car/favoris/{userId}")
    public ResponseEntity<List<CarDto>> getCarFavorisByUserId(@PathVariable Long userId) {
        List<CarDto> favoris = customerService.getCarFavorisByUserId(userId);
        return ResponseEntity.ok(favoris);
    }
}

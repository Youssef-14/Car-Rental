package com.carrentalbackend.admin;

import com.carrentalbackend.booking.BookACarDto;
import com.carrentalbackend.cars.CarDto;
import com.carrentalbackend.cars.dto.SearchCarDto;
import com.carrentalbackend.reclamations.DTOs.GetReclamationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ? is a wildcard that represents an unknown type
    @PostMapping("/car")
    public ResponseEntity<?> postCar(@ModelAttribute CarDto carDto) throws IOException {
        boolean isSuccessful = adminService.postCar(carDto);

        if (isSuccessful) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getAllCars() {
        return ResponseEntity.ok(adminService.getAllCars());
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/customers/count")
    public ResponseEntity<Integer> getTotalCustomersCount() {
        int totalCustomersCount = adminService.getTotalCustomersCount();
        return ResponseEntity.ok(totalCustomersCount);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        adminService.deleteCar(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getCarById(id));
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable Long id, @ModelAttribute CarDto carDto) throws IOException {
        try {
            boolean isSuccessful = adminService.updateCar(id, carDto);

            if (isSuccessful) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // number of cars
    @GetMapping("/cars/count")
    public ResponseEntity<Integer> getTotalCarsCount() {
        int totalCarsCount = adminService.getTotalCarsCount();
        return ResponseEntity.ok(totalCarsCount);
    }

    @PutMapping("/car/hide/{id}")
    public ResponseEntity<Void> hideShowCar(@PathVariable Long id) throws IOException {
        try {
            adminService.hideShowCar(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/car/bookings")
    public ResponseEntity<List<BookACarDto>> getBookings() {
        return ResponseEntity.ok(adminService.getBookings());
    }

    @GetMapping("/car/booking/{bookingId}/{status}")
    public ResponseEntity<Void> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status) {
        boolean isSuccessful = adminService.changeBookingStatus(bookingId, status);

        if (isSuccessful) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(@RequestBody SearchCarDto searchCarDto) {
        return ResponseEntity.ok(adminService.searchCar(searchCarDto));
    }

    @GetMapping("/car/bookings/user/{userId}")
    public ResponseEntity<List<BookACarDto>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.getBookingsByUserId(userId));
    }

    @GetMapping("/car/bookings/car/{carId}")
    public ResponseEntity<List<BookACarDto>> getBookingsByCarId(@PathVariable Long carId) {
        return ResponseEntity.ok(adminService.getBookingsByCarId(carId));
    }

    @GetMapping("/car/bookings/status/{status}")
    public ResponseEntity<List<BookACarDto>> getBookingsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(adminService.getBookingsByStatus(status));
    }

    @GetMapping("/car/booking/{id}")
    public ResponseEntity<BookACarDto> getBookingById(@PathVariable Long id) {
        BookACarDto booking = adminService.getBookingById(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/car/bookings/count")
    public ResponseEntity<Integer> getTotalBookingsCount() {
        return ResponseEntity.ok(adminService.getTotalBookingsCount());
    }

    @GetMapping("/car/bookings/count/user/{userId}")
    public ResponseEntity<Integer> getTotalBookingsCountByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.getTotalBookingsCountByUserId(userId));
    }

    @GetMapping("/car/bookings/count/car/{carId}")
    public ResponseEntity<Integer> getTotalBookingsCountByCarId(@PathVariable Long carId) {
        return ResponseEntity.ok(adminService.getTotalBookingsCountByCarId(carId));
    }

    @GetMapping("/bookings/count/status/{status}")
    public ResponseEntity<Integer> getTotalBookingsCountByStatus(@PathVariable String status) {
        return ResponseEntity.ok(adminService.getTotalBookingsCountByStatus(status));
    }

    @GetMapping("/car/bookings/count/user")
    public ResponseEntity<Integer> getTotalBookingsCountByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(adminService.getTotalBookingsCountByUserId(userId));
    }

    @GetMapping("/bookings/count/car")
    public ResponseEntity<Integer> getTotalBookingsCountByCar(@RequestParam Long carId) {
        return ResponseEntity.ok(adminService.getTotalBookingsCountByCarId(carId));
    }

    @GetMapping("/bookings/count/this_month")
    public ResponseEntity<Integer> getTotalBookingsCountThisMonth() {
        return ResponseEntity.ok(adminService.getTotalBookingsCountThisMonth());
    }

    @GetMapping("/revenue/total")
    public ResponseEntity<Integer> getTotalRevenue() {
        return ResponseEntity.ok(adminService.getTotalRevenue());
    }

    @GetMapping("/revenue/this_month")
    public ResponseEntity<Integer> getTotalRevenueThisMonth() {
        return ResponseEntity.ok(adminService.getTotalRevenueThisMonth());
    }

    @GetMapping("/revenue/this_week")
    public ResponseEntity<Integer> getTotalRevenueThisWeek() {
        return ResponseEntity.ok(adminService.getTotalRevenueThisWeek());
    }

    // get all reclamations
    @GetMapping("/reclamations")
    public ResponseEntity<List<GetReclamationDto>> getAllReclamations() {
        List<GetReclamationDto> reclamations = adminService.getAllReclamations();
        return ResponseEntity.ok(reclamations);
    }



}

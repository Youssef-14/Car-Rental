package com.carrentalbackend.admin;


import com.carrentalbackend.authentification.enums.Role;
import com.carrentalbackend.booking.BookACar;
import com.carrentalbackend.booking.BookACarDto;
import com.carrentalbackend.booking.BookACarRepository;
import com.carrentalbackend.booking.BookCarStatus;
import com.carrentalbackend.cars.*;
import com.carrentalbackend.cars.dto.CarDtoListDto;
import com.carrentalbackend.cars.dto.SearchCarDto;
import com.carrentalbackend.users.User;
import com.carrentalbackend.users.UserRepository;
import com.carrentalbackend.users.dto.UserDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final CarRepository carRepository;
    private final BookACarRepository bookACarRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(CarRepository carRepository, BookACarRepository bookACarRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.bookACarRepository = bookACarRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean postCar(CarDto carDto) throws IOException {
        boolean isSuccessful = false;

        try {
            Car car = new Car();
            car.setName(carDto.getName());
            car.setBrand(carDto.getBrand());
            car.setColor(carDto.getColor());
            car.setDescription(carDto.getDescription());
            car.setPrice(carDto.getPrice());
            car.setTransmission(carDto.getTransmission());
            car.setType(carDto.getType());
            car.setYear(carDto.getYear());
            car.setImage(carDto.getImage().getBytes());

            carRepository.save(car);

            isSuccessful = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }



    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllUsers() {
        //get ALL customers
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == Role.CUSTOMER) // Filter to get only customers
                .map(User::getUserDto) // Convert User to UserDto
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalCustomersCount() {
        return userRepository.countByRole(Role.CUSTOMER); // Count the total number of customers
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto getCarById(Long id) {
        return carRepository.findById(id).map(Car::getCarDto).orElse(null); // map() is a method that applies a given function to each element of a stream
    }

    @Override
    public int getTotalCarsCount() {
        return (int) carRepository.count(); // Count the total number of cars
    }

    @Override
    public boolean updateCar(Long id, CarDto carDto) throws IOException {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();

            if (carDto.getImage() != null) {
                existingCar.setImage(carDto.getImage().getBytes());
            }

            existingCar.setPrice(carDto.getPrice());
            existingCar.setYear(carDto.getYear());
            existingCar.setType(carDto.getType());
            existingCar.setDescription(carDto.getDescription());
            existingCar.setTransmission(carDto.getTransmission());
            existingCar.setColor(carDto.getColor());
            existingCar.setName(carDto.getName());
            existingCar.setBrand(carDto.getBrand());

            carRepository.save(existingCar);

            return true;
        }

        return false;
    }

    @Override
    public List<BookACarDto> getBookings() {
        return bookACarRepository.findAll().stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
    }

    @Override
    public List<BookACarDto> getBookingsByUserId(Long userId) {
        return bookACarRepository.findAllByUserId(userId).stream()
                .map(BookACar::getBookACarDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookACarDto> getBookingsByCarId(Long carId) {
        return bookACarRepository.findAllByCarId(carId).stream()
                .map(BookACar::getBookACarDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookACarDto> getBookingsByStatus(String status) {
        BookCarStatus bookCarStatus;

        try {
            bookCarStatus = BookCarStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return List.of(); // Return an empty list if the status is invalid
        }

        return bookACarRepository.findAllByBookCarStatus(bookCarStatus).stream()
                .map(BookACar::getBookACarDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookACarDto getBookingById(Long id) {
        return bookACarRepository.findById(id)
                .map(BookACar::getBookACarDto)
                .orElse(null); // Return null if the booking is not found
    }

    @Override
    public int getTotalBookingsCount() {
        return (int) bookACarRepository.count(); // Count the total number of bookings
    }

    @Override
    public int getTotalBookingsCountThisMonth() {
        return bookACarRepository.countBookingsThisMonth(BookCarStatus.APPROVED); // Count bookings for the current month
    }

    @Override
    public int getTotalBookingsCountByUserId(Long userId) {
        return bookACarRepository.findAllByUserId(userId).size(); // Count bookings by user ID
    }

    @Override
    public int getTotalBookingsCountByCarId(Long carId) {
        return bookACarRepository.findAllByCarId(carId).size(); // Count bookings by car ID
    }

    @Override
    public int getTotalBookingsCountByStatus(String status) {
        BookCarStatus bookCarStatus;

        try {
            bookCarStatus = BookCarStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return 0; // Return 0 if the status is invalid
        }

        return bookACarRepository.findAllByBookCarStatus(bookCarStatus).size(); // Count bookings by status
    }

    @Override
    public boolean changeBookingStatus(Long id, String status) {
        Optional<BookACar> optionalBookACar = bookACarRepository.findById(id);

        if (optionalBookACar.isPresent()) {
            BookACar bookACar = optionalBookACar.get();

            if (Objects.equals(status, "Approve")) {
                bookACar.setBookCarStatus(BookCarStatus.APPROVED);
            } else {
                bookACar.setBookCarStatus(BookCarStatus.REJECTED);
            }

            bookACarRepository.save(bookACar);

            return true;
        }

        return false;
    }

    @Override
    public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
        Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission(searchCarDto.getTransmission());
        car.setColor(searchCarDto.getColor());

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()).withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()).withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()).withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Car> carExample = Example.of(car, exampleMatcher);

        List<Car> carList = carRepository.findAll(carExample);

        CarDtoListDto carDtoListDto = new CarDtoListDto();
        carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));

        return carDtoListDto;
    }

    @Override
    public void hideShowCar(Long id) throws IOException {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setAvailable(!car.getAvailable()); // Toggle the hidden status
            carRepository.save(car);
        } else {
            throw new IOException("Car not found with id: " + id);
        }
    }
}

package com.carrentalbackend.customers;

import com.carrentalbackend.booking.BookACar;
import com.carrentalbackend.booking.BookACarDto;
import com.carrentalbackend.booking.BookACarRepository;
import com.carrentalbackend.booking.BookCarStatus;
import com.carrentalbackend.cars.Car;
import com.carrentalbackend.cars.CarDto;
import com.carrentalbackend.cars.CarRepository;
import com.carrentalbackend.users.User;
import com.carrentalbackend.users.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookACarRepository bookACarRepository;

    public CustomerServiceImpl(CarRepository carRepository, UserRepository userRepository, BookACarRepository bookACarRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.bookACarRepository = bookACarRepository;
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(BookACarDto bookACarDto) {
        Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCarId());
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());

        if (optionalCar.isPresent() && optionalUser.isPresent()) {
            Car existingCar = optionalCar.get();

            BookACar bookACar = new BookACar();
            bookACar.setUser(optionalUser.get());
            bookACar.setCar(existingCar);
            bookACar.setBookCarStatus(BookCarStatus.PENDING);

            bookACar.setDays(bookACarDto.getDays());

            bookACar.setFromDate(bookACarDto.getFromDate());
            bookACar.setToDate(bookACarDto.getToDate());
            bookACar.setPrice(bookACarDto.getDays() * existingCar.getPrice());

            bookACarRepository.save(bookACar);
            return true;
        }

        return false;
    }

    @Override
    public CarDto getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(Car::getCarDto).orElse(null);
    }

    @Override
    public List<BookACarDto> getBookingsByUserId(Long userId) {
        return bookACarRepository.findAllByUserId(userId).stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
    }

    @Override
    public List<BookACarDto> getCarDisponibility(Long carId) {
        // Get all bookings for the car for the next three months
        return bookACarRepository.findAllByCarId(carId).stream().map(BookACar::getBookACarDto).toList().stream().filter(bookACarDto -> bookACarDto.getToDate().after(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))).collect(Collectors.toList());
    }
}

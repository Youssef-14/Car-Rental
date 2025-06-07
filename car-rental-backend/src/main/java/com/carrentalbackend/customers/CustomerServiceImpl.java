package com.carrentalbackend.customers;

import com.carrentalbackend.booking.*;
import com.carrentalbackend.carfavoris.AddCarFavorisDto;
import com.carrentalbackend.carfavoris.CarFavoris;
import com.carrentalbackend.carfavoris.CarFavorisRepository;
import com.carrentalbackend.cars.Car;
import com.carrentalbackend.cars.CarDto;
import com.carrentalbackend.cars.CarRepository;
import com.carrentalbackend.customers.dto.UpdateProfileDto;
import com.carrentalbackend.users.User;
import com.carrentalbackend.users.UserRepository;
import com.carrentalbackend.users.dto.UserDto;
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
    private final CarFavorisRepository carFavorisRepository;

    public CustomerServiceImpl(CarRepository carRepository, UserRepository userRepository, BookACarRepository bookACarRepository, CarFavorisRepository carFavorisRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.bookACarRepository = bookACarRepository;
        this.carFavorisRepository = carFavorisRepository;
    }

    @Override
    public boolean updateProfile(UpdateProfileDto updateProfileDto) {
        Optional<User> optionalUser = userRepository.findById(updateProfileDto.getId());

        try {
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setFirstname(updateProfileDto.getFirstName());
                user.setLastname(updateProfileDto.getLastName());
                user.setNumber(updateProfileDto.getPhoneNumber());
                user.setAddress(updateProfileDto.getAddress());
                user.setLicenseNumber(updateProfileDto.getLicenseNumber());

                if (updateProfileDto.getLicenseImage() != null) {
                    user.setLicenseImage(updateProfileDto.getLicenseImage().getBytes());
                }

                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDto getProfile(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(User::getUserDto).orElse(null);
    }

    @Override
    public List<CarDto> getAllCars() {
        // get cars where isAvailable is true
        return carRepository.findAll().stream()
                .filter(Car::getAvailable)
                .map(Car::getCarDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(AddBookACarDto bookACarDto) {
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
    public int getBookingsCountByUserIdAndStatus(Long userId, String status) {
        BookCarStatus bookCarStatus = BookCarStatus.valueOf(status.toUpperCase());
        List<BookACar> bookACars = bookACarRepository.findAllByUserId(userId);
        return (int) bookACars.stream()
                .filter(bookACar -> bookACar.getBookCarStatus() == bookCarStatus)
                .count();
    }

    @Override
    public List<BookACarDto> getCarDisponibility(Long carId) {
        // Get all bookings for the car for the next three months
        return bookACarRepository.findAllByCarId(carId).stream().map(BookACar::getBookACarDto).toList().stream().filter(bookACarDto -> bookACarDto.getToDate().after(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))).collect(Collectors.toList());
    }

    @Override
    public void addCarToFavoris(AddCarFavorisDto addCarFavorisDto) {
        Optional<User> optionalUser = userRepository.findById(addCarFavorisDto.getUserId());
        Optional<Car> optionalCar = carRepository.findById(addCarFavorisDto.getCarId());

        if (optionalUser.isPresent() && optionalCar.isPresent()) {
            User user = optionalUser.get();
            Car car = optionalCar.get();

            // Check if the car is already in favoris else delete it
            Optional<CarFavoris> existingFavoris = carFavorisRepository.findByUserIdAndCarId(user.getId(), car.getId());
            if (existingFavoris.isPresent()) {
                carFavorisRepository.delete(existingFavoris.get());
            } else {
                CarFavoris carFavoris = new CarFavoris();
                carFavoris.setUser(user);
                carFavoris.setCar(car);
                carFavorisRepository.save(carFavoris);
            }

        } else {
            throw new IllegalArgumentException("User or Car not found");
        }
    }

    @Override
    public int getTotalCarAvailableCount() {
        return (int) carRepository.countByAvailableTrue(); // Count the total number of available cars
    }

    @Override
    public List<CarDto> getCarFavorisByUserId(Long userId) {
        List<CarFavoris> carFavorisList = carFavorisRepository.findFavorisByUserId(userId);

        if (carFavorisList.isEmpty()) {
            return List.of(); // Return an empty list if no favoris found
        }

        List<CarDto> list = carFavorisList.stream()
                .map(CarFavoris::getCar)
                .map(Car::getCarDto)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public boolean cancelBooking(Long bookingId) {
        Optional<BookACar> optionalBooking = bookACarRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            BookACar booking = optionalBooking.get();
            booking.setBookCarStatus(BookCarStatus.CANCELED);
            bookACarRepository.save(booking);
            return true;
        }
        return false;
    }
}

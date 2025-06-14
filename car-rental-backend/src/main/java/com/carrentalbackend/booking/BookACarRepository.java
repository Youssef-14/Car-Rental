package com.carrentalbackend.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookACarRepository extends JpaRepository<BookACar, Long> {

    List<BookACar> findAllByUserId(Long userId);

    List<BookACar> findAllByCarId(Long carId);

    List<BookACar> findAllByBookCarStatus(BookCarStatus status);

    @Query("SELECT COUNT(b) FROM BookACar b " +
            "WHERE MONTH(b.fromDate) = MONTH(CURRENT_DATE) " +
            "AND YEAR(b.fromDate) = YEAR(CURRENT_DATE) " +
            "AND b.bookCarStatus = :status")
    int countBookingsThisMonth(@Param("status") BookCarStatus status);

    // liste of bookings this month by status
    @Query("SELECT b FROM BookACar b " +
            "WHERE MONTH(b.fromDate) = MONTH(CURRENT_DATE) " +
            "AND YEAR(b.fromDate) = YEAR(CURRENT_DATE)" +
            "AND b.bookCarStatus = :status")
    List<BookACar> findBookingsThisMonthByStatus(@Param("status") BookCarStatus status);

    // list of bookings this week by status
    @Query("SELECT b FROM BookACar b " +
            "WHERE WEEK(b.fromDate) = WEEK(CURRENT_DATE) " +
            "AND YEAR(b.fromDate) = YEAR(CURRENT_DATE) " +
            "AND b.bookCarStatus = :status")
    List<BookACar> findBookingsThisWeekByStatus(@Param("status") BookCarStatus status);

    // list of bookings this next two months by status and car id
    @Query("SELECT b FROM BookACar b " +
            "WHERE MONTH(b.fromDate) IN (MONTH(CURRENT_DATE), MONTH(CURRENT_DATE) + 1) " +
            "AND YEAR(b.fromDate) = YEAR(CURRENT_DATE) " +
            "AND b.bookCarStatus = :status " +
            "AND b.car.id = :carId")
    List<BookACar> findBookingsNextTwoMonthsByStatusAndCarId(@Param("status") BookCarStatus status, @Param("carId") Long carId);

    int countByUserId(Long userId);
}

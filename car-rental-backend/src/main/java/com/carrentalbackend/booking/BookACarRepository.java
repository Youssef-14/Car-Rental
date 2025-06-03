package com.carrentalbackend.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    int countByUserId(Long userId);
}

package com.carrentalbackend.carfavoris;

import com.carrentalbackend.cars.Car;
import com.carrentalbackend.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarFavorisRepository extends JpaRepository<CarFavoris, Long> {

    // Méthode pour trouver les favoris d'un utilisateur
    //return liste de voiture favoris par utilisateur
    @Query("SELECT cf FROM CarFavoris cf WHERE cf.user.id = :userId")
    List<CarFavoris> findFavorisByUserId(@Param("userId") Long userId);


    // Méthode pour trouver un favori spécifique par utilisateur et voiture
    Optional<CarFavoris> findByUserIdAndCarId(Long userId, Long carId);

    // Méthode pour supprimer un favori par utilisateur et voiture
    void deleteByUserIdAndCarId(Long userId, Long carId);

    boolean existsByUserAndCar(User user, Car car);
}

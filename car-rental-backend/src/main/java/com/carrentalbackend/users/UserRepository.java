package com.carrentalbackend.users;

import java.util.Optional;

import com.carrentalbackend.authentification.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  int countByRole(Role role);
}

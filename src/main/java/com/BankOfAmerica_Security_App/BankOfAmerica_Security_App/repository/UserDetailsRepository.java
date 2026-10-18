package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.repository;

import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<Users , Long> {

    Optional<Users> findByUsername(String username);
}

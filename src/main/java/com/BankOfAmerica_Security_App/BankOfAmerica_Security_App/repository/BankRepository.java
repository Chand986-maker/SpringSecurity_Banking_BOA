package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.repository;


import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<BankAccount, Long> {

}

package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String customerName;

    private String accountType;

    private String branch;

    private Double balance;
}
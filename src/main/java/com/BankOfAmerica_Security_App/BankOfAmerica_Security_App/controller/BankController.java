package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.controller;


import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.BankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.service.BankService;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {


    // POST http://localhost:8080/api/bank/accounts

/*    {
    "username": "chandu",
    "password": "chandu123",
    "role": "ROLE_USER"
}*/

   // GET http://localhost:8080/api/bank/accounts

/*            [
    {
        "accountId": 1,
            "customerName": "Chandu",
            "accountType": "SAVINGS",
            "branch": "Bangalore",
            "balance": 50000
    }
]*/


    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    // 1. CREATE
    @PostMapping("/accounts")
    @PreAuthorize("hasAnyAuthority('BANK_WRITE')")

/*
    this annatation @PreAuthorize() will help us to know which is shaving authority to perform task
*/
    public ResponseEntity<BankAccount> createAccount(
            @RequestBody BankAccount account) {

        return ResponseEntity.ok(
                bankService.createAccount(account)
        );
    }

    // 2. READ
    @GetMapping("/accounts")
    @PreAuthorize("hasAnyAuthority('BANK_READ')")
    public ResponseEntity<List<BankAccount>> getAllAccounts() {

        return ResponseEntity.ok(
                bankService.getAllAccounts()
        );
    }

    // 3. UPDATE
    @PutMapping("/accounts/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")   // we can use multiple ROLES if you want
    public ResponseEntity<BankAccount> updateAccount(
            @PathVariable Long id,
            @RequestBody BankAccount account) {

        return ResponseEntity.ok(
                bankService.updateAccount(id, account)
        );
    }

    // 4. DELETE
    @DeleteMapping("/accounts/{id}")
    @PreAuthorize("hasAnyAuthority('BANK_DELETE')")
    public ResponseEntity<String> deleteAccount(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bankService.deleteAccount(id)
        );
    }

// testing : http://localhost:8080/api/bank/testing
    @GetMapping("/testing")
    @PreAuthorize("hasAnyAuthority('BANK_READ')")
    public String sampleData(){
        return "this is just for testing ...";
    }
}
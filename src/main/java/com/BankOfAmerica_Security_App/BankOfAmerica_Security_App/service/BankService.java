package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.service;



import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.BankAccount;
import org.springframework.stereotype.Service;
import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.repository.BankRepository;

import java.util.List;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    // CREATE
    public BankAccount createAccount(BankAccount account) {

        return bankRepository.save(account);
    }

    // READ
    public List<BankAccount> getAllAccounts() {

        return bankRepository.findAll();
    }

    // UPDATE
    public BankAccount updateAccount(Long id, BankAccount account) {

        BankAccount existingAccount =
                bankRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Account not found"));

        existingAccount.setCustomerName(account.getCustomerName());
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBranch(account.getBranch());
        existingAccount.setBalance(account.getBalance());

        return bankRepository.save(existingAccount);
    }

    // DELETE
    public String deleteAccount(Long id) {

        if (!bankRepository.existsById(id)) {
            return "Account not found";
        }

        bankRepository.deleteById(id);

        return "Account deleted successfully";
    }
}
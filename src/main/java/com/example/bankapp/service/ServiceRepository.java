package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDTO;

import java.util.List;

public interface ServiceRepository {
    List<AccountDTO> getAccounts();
    AccountDTO getAccountById(Long id);
    AccountDTO createAccount(AccountDTO accountDTO);
    void deleteAccount(Long id);
    AccountDTO deposit(Long id,double amount);
    AccountDTO withdraw(Long id,double amount);
}

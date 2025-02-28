package com.example.bankapp.mapper;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;

public class AccountMapper {
    // Mapping AccountDto to Account
    public static Account maptoAccount(AccountDTO accountDTO) {
        Account account = new Account(accountDTO.id(), accountDTO.balance(), accountDTO.accountHolderName());
        return account;
    }
    // Mapping Account to AccountDTO
    public static AccountDTO maptoAccountDTO(Account account) {
        AccountDTO accountDTO= new AccountDTO(account.getId(), account.getBalance(), account.getAccountHolderName());
    return accountDTO;
    }
}
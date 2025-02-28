package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.exception.AccountException;
import com.example.bankapp.mapper.AccountMapper;
import com.example.bankapp.repository.AccountRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ServiceRepositoryImpl implements ServiceRepository {
   private final AccountRepository accountRepository;

public ServiceRepositoryImpl(AccountRepository accountRepository) {
    this.accountRepository =accountRepository;
}
//List<AccountDTO> getAccounts();
    @Override
    public List<AccountDTO> getAccounts() {
    List<Account> accounts= accountRepository.findAll();
    return accounts.stream().map(account->AccountMapper.maptoAccountDTO(account)).
        collect(Collectors.toList());
    }
// AccountDTO getAccountById(Long id);
    @Override
    public AccountDTO getAccountById(Long id) {
    Account account=accountRepository.findById(id).
            orElseThrow(()->new RuntimeException("Account not found!"));
    return AccountMapper.maptoAccountDTO(account);
    }
// AccountDTO createAccount(AccountDTO accountDTO);
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
    Account account=AccountMapper.maptoAccount(accountDTO);
    Account savedAccount=accountRepository.save(account);
    return AccountMapper.maptoAccountDTO(savedAccount);
    }
// void deleteAccount(Long id);
    @Override
    public void deleteAccount(Long id) {
    Account account=accountRepository.findById(id).
            orElseThrow(()->new AccountException("Account not found!"));
    accountRepository.deleteById(id);
    }
// AccountDTO deposit(AccountDTO accountDTO,Long amount);
    @Override
    public AccountDTO deposit(Long id,double amount) {
    Account account=accountRepository.findById(id).
            orElseThrow(()->new AccountException("Account not found!"));
    double total=account.getBalance()+amount;
    account.setBalance(total);
    Account savedAccount=accountRepository.save(account);
    return AccountMapper.maptoAccountDTO(savedAccount);
    }
// AccountDTO withdraw(Long id,double amount);
    @Override
    public AccountDTO withdraw(Long id,double amount) {
    Account account=accountRepository.findById(id).
            orElseThrow(()->new AccountException("Account not found!"));
    if(account.getBalance()<amount){
        throw new RuntimeException("Insufficient balance!");
    }
    double total=account.getBalance()-amount;
    account.setBalance(total);
    Account savedAccount=accountRepository.save(account);
    return AccountMapper.maptoAccountDTO(savedAccount);
    }
}

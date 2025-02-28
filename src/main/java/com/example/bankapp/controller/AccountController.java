package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.service.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final ServiceRepository serviceRepository;
    public AccountController(ServiceRepository serviceRepository) {
        this.serviceRepository=serviceRepository;
    }
    // Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accounts=serviceRepository.getAccounts();
        return ResponseEntity.ok(accounts);
    }
    // Get Account REST API by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable ("id")Long id){
        AccountDTO accountDTO=serviceRepository.getAccountById(id);
        return ResponseEntity.ok(accountDTO);
    }
    // Add account REST API
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO){
       return new ResponseEntity<>(serviceRepository.createAccount(accountDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO>depositAccount(@PathVariable("id")Long id,
                                                    @RequestBody Map<String,Double>request){
        double amount=request.get("amount");
        AccountDTO accountDTO=serviceRepository.deposit(id,amount);
        return ResponseEntity.ok(accountDTO);
    }
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdrawAccount(@PathVariable("id")Long id,
                                                      @RequestBody Map<String,Double> request){
        double amount=request.get("amount");
        AccountDTO accountDTO=serviceRepository.withdraw(id,amount);
        return ResponseEntity.ok(accountDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id")Long id){
        serviceRepository.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully!");
    }
}

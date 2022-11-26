package com.example.WebBankingApp.service;

import com.example.WebBankingApp.model.Account;
import com.example.WebBankingApp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class AccountService {

    private final AccountRepository accountRepository;

    public Account create(Account account) {
        log.info("Saving new Account : {}", account.getId());
        return accountRepository.save(account);
    }

    public Collection<Account> list(int limit) {
        log.info("Fetching all accounts");
        return accountRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    public Account credit(Long id, double value) {
        log.info("Trying to reach the Account : {}", id);
        Account account = accountRepository.accountFindById(id);
        account.setAccountBalance(account.getAccountBalance() + value);
        return account;
    }

    public Account debit(Long id, double value) {
        log.info("Trying to reach the Account : {}", id);
        Account account = accountRepository.accountFindById(id);
        account.setAccountBalance(account.getAccountBalance() + value);
        return account;
    }

    public Account get(Long id) {
        log.info("Fetching accounts by id : {}", id);
        return accountRepository.findById(id).get();
    }

    public Account update(Account account) {
        log.info("Updating account : {}", account.getId());
        return accountRepository.save(account);
    }

    public Boolean delete(Long id) {
        log.info("Deleting account by ID : {}", id);
        accountRepository.deleteById(id);
        return true;
    }

}

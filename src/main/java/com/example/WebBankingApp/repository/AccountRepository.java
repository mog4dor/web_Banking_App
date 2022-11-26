package com.example.WebBankingApp.repository;

import com.example.WebBankingApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account accountFindByEmail(String ownerEmail);

    public Account accountFindById(Long id);
}

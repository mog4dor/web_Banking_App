package com.example.WebBankingApp.resource;

import com.example.WebBankingApp.model.Account;
import com.example.WebBankingApp.model.Response;
import com.example.WebBankingApp.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountResource {
    private final AccountService accountService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() throws IOException {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("accounts", accountService.list(30)))
                        .message("Accounts retreived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/credit/{id}")
    public ResponseEntity<Response> credit(@PathVariable("id") Long id) throws IOException {
        Account account = accountService.credit(id, 50);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("account", account))
                        .message(account.getId() == id ? "Operation success" : "Operation failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/debit/{id}")
    public ResponseEntity<Response> debit(@PathVariable("id") Long id) throws IOException {
        Account account = accountService.debit(id, 10);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("account", account))
                        .message(account.getId() == id && account.getAccountBalance() - 10 > 0 ? "Operation success" : "Operation failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/save")
    public ResponseEntity<Response> debit(@RequestBody @Valid Account account) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("account", accountService.create(account)))
                        .message("Account created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
}

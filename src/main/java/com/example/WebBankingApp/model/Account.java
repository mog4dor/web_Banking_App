package com.example.WebBankingApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDate;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @NotEmpty(message = "The owner lastname cannot be empty.")
    private String ownerLastName;
    @NotEmpty(message = "The owner firstname cannot be empty.")
    private String ownerFirstName;
    @Column(unique = true)
    @NotEmpty(message = "The owner email cannot be empty.")
    private String ownerEmail;
    private LocalDate ownerDOB;
    private Integer ownerAge;
    private double accountBalance;

}

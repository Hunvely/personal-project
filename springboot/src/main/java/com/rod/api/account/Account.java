package com.rod.api.account;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(exclude = {"id"}) // id를 제외하고 출력함. 빼고 싶은 것들 넣으면 됨

public class Account {

    private long id;

    private String accountNumber;

    private String accountHolder;

    private double balance;

    private LocalDate transactionDate;

    @Builder(builderMethodName = "builder")
    public Account(long id, String accountNumber, String accountHolder, double balance, LocalDate transactionDate) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactionDate = transactionDate;
    }
}

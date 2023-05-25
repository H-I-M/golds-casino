package com.clyde.butler.goldscasino.model;

import lombok.Data;

@Data
public class UpdateBalanceRequestDTO {

    private double amount;
    private String transactionType;

}


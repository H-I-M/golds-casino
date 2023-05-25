package com.clyde.butler.goldscasino.model;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {

    private BigInteger transactionId;
    private String transactionType;
    private double amount;
    private Integer playerId;
    private LocalDateTime timestamp;

}

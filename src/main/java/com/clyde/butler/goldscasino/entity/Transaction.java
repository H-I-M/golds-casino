package com.clyde.butler.goldscasino.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction {
    @Id
    @Getter
    // Having problems with IDENTITY, org.springframework.dao.DataIntegrityViolationException;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private Integer playerId;

    @Getter
    @Setter
    private double amount;

    @Getter
    @Setter
    private String transactionId;

    @Getter
    @Setter
    private String transactionType;

    @Getter
    @Setter
    private LocalDateTime timestamp;

}

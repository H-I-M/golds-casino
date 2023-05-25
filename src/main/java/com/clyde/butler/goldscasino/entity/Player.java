package com.clyde.butler.goldscasino.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "players")
@NoArgsConstructor
public class Player {
    @Id
    @Getter
    // Having problems with IDENTITY, org.springframework.dao.DataIntegrityViolationException;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playerId;

    @Getter
    @Setter
    String username;

    @Getter
    @Setter
    private double balance;

}

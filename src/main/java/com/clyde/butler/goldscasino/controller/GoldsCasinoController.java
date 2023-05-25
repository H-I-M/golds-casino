package com.clyde.butler.goldscasino.controller;

import com.clyde.butler.goldscasino.exception.BadRequestException;
import com.clyde.butler.goldscasino.exception.PlayerNotFoundException;
import com.clyde.butler.goldscasino.model.*;
import com.clyde.butler.goldscasino.service.CasinoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/casino")
@AllArgsConstructor
public class GoldsCasinoController {

    private final CasinoService casinoService;

    @PostMapping("/player/create")
    public ResponseEntity<?> createPlayer(@RequestBody @Valid PlayerRequestDTO requestDTO) {

        try {
            PlayerDTO player = casinoService.createPlayer(requestDTO.getUserName(), requestDTO.getBalance());
            return ResponseEntity.ok(player);
        }
        catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/player/{playerId}/balance")
    public ResponseEntity<?> getBalance(@PathVariable("playerId") Integer playerId) {

        try {
            BalanceResponseDTO balance = casinoService.getBalance(playerId);
            return ResponseEntity.ok(balance);
        }
        catch (PlayerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/player/{playerId}/balance/update")
    public ResponseEntity<?> updateBalance(@PathVariable("playerId") Integer playerId,
                                                        @RequestBody @Valid UpdateBalanceRequestDTO requestDTO) {
        try {
            TransactionDTO transaction = casinoService.updateBalance(playerId, requestDTO.getAmount(), requestDTO.getTransactionType());
            return ResponseEntity.ok(transaction);
        }
        catch (Exception e) {

           if(e instanceof BadRequestException || e instanceof PlayerNotFoundException) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
           }

           return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
        }
    }

    @PostMapping("/admin/player/transactions")
    public ResponseEntity<?> getLastTenTransactions(@RequestParam("username") String username) {

        try {
            List<TransactionDTO> transactions = casinoService.getLastTenTransactions(username);
            return ResponseEntity.ok(transactions);
        }
        catch (PlayerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

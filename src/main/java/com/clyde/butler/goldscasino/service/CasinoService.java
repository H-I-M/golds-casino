package com.clyde.butler.goldscasino.service;


import com.clyde.butler.goldscasino.exception.BadRequestException;
import com.clyde.butler.goldscasino.exception.PlayerNotFoundException;
import com.clyde.butler.goldscasino.model.BalanceResponseDTO;
import com.clyde.butler.goldscasino.model.PlayerDTO;
import com.clyde.butler.goldscasino.model.TransactionDTO;

import java.util.List;

public interface CasinoService {
    PlayerDTO createPlayer(String userName, double balance) throws BadRequestException;

    BalanceResponseDTO getBalance(Integer playerId) throws PlayerNotFoundException;

    TransactionDTO updateBalance(Integer playerId, double amount, String transactionType) throws Exception;

    List<TransactionDTO> getLastTenTransactions(String username) throws PlayerNotFoundException;
}


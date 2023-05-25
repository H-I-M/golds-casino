package com.clyde.butler.goldscasino.service;

import com.clyde.butler.goldscasino.entity.Player;
import com.clyde.butler.goldscasino.entity.Transaction;
import com.clyde.butler.goldscasino.exception.BadRequestException;
import com.clyde.butler.goldscasino.exception.PlayerNotFoundException;
import com.clyde.butler.goldscasino.exception.TeapotException;
import com.clyde.butler.goldscasino.model.BalanceResponseDTO;
import com.clyde.butler.goldscasino.model.PlayerDTO;
import com.clyde.butler.goldscasino.model.TransactionDTO;
import com.clyde.butler.goldscasino.repository.PlayerRepository;
import com.clyde.butler.goldscasino.repository.TransactionRepository;
import com.clyde.butler.goldscasino.util.CasinoUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CasinoServiceImpl implements CasinoService {

    private final PlayerRepository playerRepository;
    private final TransactionRepository transactionRepository;
    private final CasinoUtils casinoUtils;
    private static final String WAGER_TRANSACTION = "WAGER";
    private static final String WIN_TRANSACTION = "WIN";
    private static final String AMOUNT_ERROR = "Invalid amount value.";

    @Override
    public BalanceResponseDTO getBalance(Integer playerId) throws PlayerNotFoundException {
        Player player = getPlayerById(playerId);

        if (player == null) {
            throw new PlayerNotFoundException();
        }

        BalanceResponseDTO balanceDTO = new BalanceResponseDTO();
        balanceDTO.setPlayerId(player.getPlayerId());
        balanceDTO.setBalance(player.getBalance());

        return balanceDTO;
    }

    @Override
    public TransactionDTO updateBalance(Integer playerId, double amount, String transactionType) throws Exception {
        Player player = getPlayerById(playerId);

        if (player == null) {
            throw new PlayerNotFoundException();
        }

        double currentBalance = player.getBalance();

        if (amount <= 0) {
            throw new BadRequestException(AMOUNT_ERROR);
        }
        if (transactionType.equalsIgnoreCase(WAGER_TRANSACTION) && amount > currentBalance) {
            throw new TeapotException();
        }

        if (transactionType.equalsIgnoreCase(WAGER_TRANSACTION)) {
            player.setBalance(currentBalance - amount);
        }
        else if (transactionType.equalsIgnoreCase(WIN_TRANSACTION)) {
            player.setBalance(currentBalance + amount);
        }

        try {
            Transaction transaction = new Transaction();
            transaction.setPlayerId(player.getPlayerId());
            transaction.setAmount(amount);
            transaction.setTransactionId(CasinoUtils.generateTransactionId());
            transaction.setTransactionType(transactionType);
            transaction.setTimestamp(LocalDateTime.now());
            transactionRepository.save(transaction);

            playerRepository.save(player);

            return casinoUtils.transactionEntityToDto(transaction);

        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public List<TransactionDTO> getLastTenTransactions(String username) throws PlayerNotFoundException {
        Player player = playerRepository.findByUsername(username);

        if (player == null) {
            throw new PlayerNotFoundException();
        }

        return casinoUtils.transactionEntityToDto(transactionRepository.findTop10PlayerTransactionsByPlayerId(player.getPlayerId()));
    }

    @Override
    public PlayerDTO createPlayer(String username, double creditBalance) throws BadRequestException {
        try {
            Player player = new Player();
            player.setUsername(username);
            player.setBalance(creditBalance < 0 ? 0 : creditBalance);

            return casinoUtils.playerEntityToDto(playerRepository.save(player));
        }
        catch (Exception e) {
            throw new BadRequestException();
        }
    }

    private Player getPlayerById(Integer playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

}
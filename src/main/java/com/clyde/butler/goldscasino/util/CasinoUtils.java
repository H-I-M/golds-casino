package com.clyde.butler.goldscasino.util;

import com.clyde.butler.goldscasino.entity.Player;
import com.clyde.butler.goldscasino.entity.Transaction;
import com.clyde.butler.goldscasino.model.PlayerDTO;
import com.clyde.butler.goldscasino.model.TransactionDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@NoArgsConstructor
public class CasinoUtils {

    public TransactionDTO transactionEntityToDto(Transaction entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId(new BigInteger(entity.getTransactionId()));
        dto.setTransactionType(entity.getTransactionType());
        dto.setAmount(entity.getAmount());
        dto.setPlayerId(entity.getPlayerId());
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }

    public List<TransactionDTO> transactionEntityToDto(List<Transaction> entities) {
        List<TransactionDTO> dtoList = new ArrayList<>();
        TransactionDTO dto;

        for (Transaction tx : entities) {
            dto = transactionEntityToDto(tx);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public PlayerDTO playerEntityToDto(Player entity) {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(entity.getPlayerId());
        dto.setUsername(entity.getUsername());
        dto.setBalance(entity.getBalance());

        return dto;
    }

    public static String generateTransactionId() {
        String uuid = UUID.randomUUID().toString().replaceAll("\\D+", "");

        return formatGeneratedString(uuid, 6);
    }

    private static String formatGeneratedString(String uuid, int maxDigits) {
        if (uuid.length() > maxDigits) {
            return uuid.substring(0, maxDigits);
        }
        else if (uuid.length() < maxDigits) {

        // Padding the uuid with leading zeros if it has fewer digits than maxDigits
            return String.format("%0" + maxDigits + "d", new BigInteger(uuid));
        }

        return uuid;
    }
}
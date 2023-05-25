package com.clyde.butler.goldscasino.repository;

import com.clyde.butler.goldscasino.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findTop10PlayerTransactionsByPlayerId(Integer playerId);
}

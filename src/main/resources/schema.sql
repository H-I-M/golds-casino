CREATE TABLE IF NOT EXISTS players (
  player_id INTEGER PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  balance DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS transactions (
  id BIGINT PRIMARY KEY,
  player_id INTEGER,
  amount DECIMAL(10, 2),
  transaction_id VARCHAR(10),
  transaction_type VARCHAR(6),
  timestamp TIMESTAMP,
  FOREIGN KEY (player_id) REFERENCES players(player_id)
);

--INSERT INTO players (player_id, username, balance) VALUES (1, 'John', 200);
--INSERT INTO players (player_id, username, balance) VALUES (2, 'Doe', 75);
--INSERT INTO transactions (id, player_id, amount, transaction_id, transaction_type, timestamp) VALUES (1, 1, 15, '4000', 'WAGER', CURRENT_TIMESTAMP);
--INSERT INTO transactions (id, player_id, amount, transaction_id, transaction_type, timestamp) VALUES (2, 2, 25, '4001', 'WIN', CURRENT_TIMESTAMP);

package com.clyde.butler.goldscasino.exception;

public class PlayerNotFoundException extends Exception {
    public PlayerNotFoundException() {
        super("Player not found.");
    }
}
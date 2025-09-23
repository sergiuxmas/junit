package com.endava.internship.workshop;

import java.util.Objects;

public class BankAccount {
    private final String owner;
    private double balance;

    public BankAccount(String owner, double initialBalance) {
        this.owner = Objects.requireNonNull(owner, "owner cannot be null");
        if (initialBalance < 0) throw new IllegalArgumentException("Initial balance must be >= 0");
        this.balance = initialBalance;
    }

    public String owner() {
        return owner;
    }

    public double balance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be > 0");
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw must be > 0");
        if (amount > balance) throw new IllegalStateException("Insufficient funds");
        balance -= amount;
    }

    public boolean isOverdrawn() {
        return balance < 0;
    }
}
package com.endava.internship.test;

import com.endava.internship.workshop.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BankAccountSecondTest {

    @Test
    public void validAccountCreation() {
        String owner = "Alice";
        double initialBalance = 100;
        BankAccount bankAccount = new BankAccount(owner, initialBalance);

        Assertions.assertNotNull(bankAccount, "Bank account should not be null");
    }

    @Test
    public void invalidAccountCreationWhenMissingOwner() {
        double initialBalance = 100;
        Assertions.assertThrows(NullPointerException.class, () -> new BankAccount(null, initialBalance));
    }

    @DisplayName("Test invalid account creation with various invalid inputs")
    @ParameterizedTest
    @CsvSource({
            "Alice, -0.01",
            "Bob, -50"
    })
    public void invalidAccountCreation(String owner, double amount) {
        BankAccount bankAccount = new BankAccount("Alice", 0);
        Assertions.assertNotNull(bankAccount, "Bank account should not be null");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BankAccount(owner, amount),
                "Amount should not be negative");
    }

    @Test
    public void validDeposit() {
        BankAccount bankAccount = new BankAccount("Alice", 100);
        bankAccount.deposit(50);
        Assertions.assertEquals(150, bankAccount.balance(), "Balance should be 150 after deposit");
        assertThat(bankAccount.balance(), is(150.0));
    }
}

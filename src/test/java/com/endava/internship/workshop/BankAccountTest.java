package com.endava.internship.workshop;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    public void validAccountCreation() {
        BankAccount account = new BankAccount("John Doe", 100.0);
        assertNotNull(account);
        assertEquals("John Doe", account.owner());
        assertEquals(100.0, account.balance());
    }

    @Test
    public void invalidAccountCreation() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> new BankAccount(null, 100.0));

        assertEquals("owner cannot be null", exception.getMessage());
    }

    @Test
    public void depositIncreasesBalance() {
        BankAccount account = new BankAccount("Alice", 100.0);
        account.deposit(50.0);
        assertThat(account.balance(), is(closeTo(150.0, 0.0001)));
    }

    @Test
    public void depositZeroThrowsException() {
        BankAccount account = new BankAccount("Bob", 100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThat(exception.getMessage(), is("Deposit must be > 0"));
    }

    @Test
    public void depositNegativeThrowsException() {
        BankAccount account = new BankAccount("Carol", 100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(-20));
        assertThat(exception.getMessage(), is("Deposit must be > 0"));
    }
}

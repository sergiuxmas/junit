package com.endava.internship.workshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    @DisplayName("Check valid ")
    public void validBankAccount(){
        BankAccount bankAccount = new BankAccount("Alice", 100);
        Assertions.assertNotNull(bankAccount);
    }
}

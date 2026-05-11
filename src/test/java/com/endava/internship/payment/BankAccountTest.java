package com.endava.internship.payment;

import com.endava.internship.workshop.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BankAccountTest {

    @Test
    @DisplayName("Check valid ")
    public void validBankAccount(){
        BankAccount bankAccount = new BankAccount("Alice", 100);
        Assertions.assertNotNull(bankAccount);
    }

    @Test
    @DisplayName("Check invalid OWNER")
    public void invalidBankAccount(){
        Assertions.assertThrows(NullPointerException.class,
                () -> new BankAccount(null, 100)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "Alice, 0",
            "Bob, 50"
    })
    public void checkDeposit(String owner, int amount) {
        BankAccount bankAccount = new BankAccount(owner, amount);
        Assertions.assertNotNull(bankAccount);
        Assertions.assertEquals(amount, bankAccount.balance());
        Assertions.assertEquals(owner, bankAccount.owner());
    }

    @Test
    @DisplayName("Check invalid OWNER")
    public void invalidDeposit(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("Alice", -100)
        );
    }
}

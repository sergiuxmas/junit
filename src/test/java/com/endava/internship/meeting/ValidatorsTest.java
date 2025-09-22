package com.endava.internship.meeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorsTest {

    @Test
    public void isValidEmailPositiveTest() {
        assertTrue(Validators.isValidEmail("abc@endava.com"));
        assertTrue(Validators.isValidEmail("user.name@endava.com"));
        assertTrue(Validators.isValidEmail("u123e@endava.com"));
    }

    @Test
    public void isValidEmailNegativeTest() {
        assertFalse(Validators.isValidEmail("plainaddress"),"Must be invalidate plainaddress");
        assertFalse(Validators.isValidEmail("abc@endava"), "Must be invalidate abc@endava");
        assertFalse(Validators.isValidEmail("@endava.com"), "Must be invalidate @endava.com");
    }

    @Test
    public void isValidTeamsLinkTest() {
        assertFalse(Validators.isValidTeamsLink("https://www.teams.microsoft.com/123"));
        assertFalse(Validators.isValidTeamsLink("http://teams.microsoft.com/123"));
        assertFalse(Validators.isValidTeamsLink("https://teams.microsoft.com"));
    }

    @Test
    public void isTeamsLinkTest() {
        assertTrue(Validators.isValidTeamsLink("https://teams.microsoft.com/123"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abc@endava.com",
            "user.name+tag@endava.com",
            "u123@endava.co.uk"
    })
    public void validEmails(String email) {
        assertTrue(Validators.isValidEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "plainaddress",
            "abc@endava",
            "@endava.com"
    })
    void invalidEmails(String email) {
        assertFalse(Validators.isValidEmail(email));
    }
}

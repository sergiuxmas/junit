package com.endava.internship.health;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public final class Validators {
    private Validators() {}

    private static final Pattern MRN = Pattern.compile("^[A-Z0-9]{8,12}$");
    
    public static boolean isValidMrn(String s) {
        return s != null && MRN.matcher(s).matches();
    }

    public static LocalDate parseIsoDate(String s) {
        if (s == null) throw new IllegalArgumentException("date is null");
        try {
            return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Bad ISO date: " + s, ex);
        }
    }
}

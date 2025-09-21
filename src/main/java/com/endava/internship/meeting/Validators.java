package com.endava.internship.meeting;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public final class Validators {
    private Validators() {
    }

    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern TEAMS = Pattern.compile("^https://teams\\.microsoft\\.com/.*");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL.matcher(email).matches();
    }

    public static boolean isValidTeamsLink(String url) {
        return url != null && TEAMS.matcher(url).matches();
    }

    public static ZonedDateTime parseZdt(String isoZdt) {
        if (isoZdt == null) throw new IllegalArgumentException("datetime is null");
        try {
            return ZonedDateTime.parse(isoZdt);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Bad ISO zoned datetime: " + isoZdt, ex);
        }
    }
}

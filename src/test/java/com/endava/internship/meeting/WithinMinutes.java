package com.endava.internship.meeting;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.Duration;
import java.time.ZonedDateTime;

public class WithinMinutes extends TypeSafeMatcher<ZonedDateTime> {
    private final ZonedDateTime expected;
    private final long minutes;

    private WithinMinutes(ZonedDateTime expected, long minutes) {
        this.expected = expected;
        this.minutes = minutes;
    }

    public static WithinMinutes withinMinutes(ZonedDateTime expected, long minutes) {
        if (minutes < 0) throw new IllegalArgumentException("minutes must be >= 0");
        return new WithinMinutes(expected, minutes);
    }

    @Override
    protected boolean matchesSafely(ZonedDateTime actual) {
        long diff = Math.abs(Duration.between(expected, actual).toMinutes());
        return diff <= minutes;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a time within ")
                .appendValue(minutes)
                .appendText(" minute(s) of ")
                .appendValue(expected);
    }

    @Override
    protected void describeMismatchSafely(ZonedDateTime item, Description mismatchDescription) {
        mismatchDescription.appendText("was ").appendValue(item);
    }
}

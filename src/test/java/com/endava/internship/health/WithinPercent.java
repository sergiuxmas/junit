package com.endava.internship.health;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class WithinPercent extends TypeSafeMatcher<Double> {
    private final double expected;
    private final double percent;

    private WithinPercent(double expected, double percent) {
        this.expected = expected;
        this.percent = percent;
    }

    public static WithinPercent withinPercent(double expected, double percent) {
        return new WithinPercent(expected, percent);
    }

    @Override
    protected boolean matchesSafely(Double actual) {
        double tolerance = Math.abs(expected) * (percent / 100.0);
        return Math.abs(actual - expected) <= tolerance;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a value within ").appendValue(percent)
                   .appendText("% of ").appendValue(expected);
    }

    @Override
    protected void describeMismatchSafely(Double item, Description mismatchDescription) {
        mismatchDescription.appendText("was ").appendValue(item);
    }
}

package com.endava.internship.meeting;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lifecycle & Parameterized tests for Teams/Online Meetings")
public class LifecycleAndParameterizedMeetingTest {

    private static ZonedDateTime BASE;
    private Scheduler scheduler;

    private static ZonedDateTime zone(String iso) {
        return Validators.parseZdt(iso);
    }

    @BeforeAll
    static void initSuite() {
        BASE = zone("2025-09-19T09:00:00+03:00[Europe/Chisinau]");
    }

    @BeforeEach
    void initCase() {
        scheduler = new Scheduler();
        assertNotNull(scheduler);
    }

    @ParameterizedTest(name = "Duration minutes from {0} to {1} == {2}")
    @CsvSource({
            "2025-09-19T10:00:00+03:00[Europe/Chisinau], 2025-09-19T11:00:00+03:00[Europe/Chisinau], 60",
            "2025-09-19T12:15:00+03:00[Europe/Chisinau], 2025-09-19T12:45:00+03:00[Europe/Chisinau], 30"
    })
    void durationParameterized(String s, String e, long minutes) {
        Meeting m = new Meeting("D", "Demo", "org@example.com", zone(s), zone(e), "https://teams.microsoft.com/join/demo");
        assertEquals(minutes, m.durationMinutes());
        MatcherAssert.assertThat(zone(s), WithinMinutes.withinMinutes(m.start(), 0));
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> joinWindowData() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("2025-09-19T10:00:00+03:00[Europe/Chisinau]", "2025-09-19T10:30:00+03:00[Europe/Chisinau]", "2025-09-19T09:55:00+03:00[Europe/Chisinau]", true),
                org.junit.jupiter.params.provider.Arguments.of("2025-09-19T10:00:00+03:00[Europe/Chisinau]", "2025-09-19T10:30:00+03:00[Europe/Chisinau]", "2025-09-19T10:40:00+03:00[Europe/Chisinau]", true),
                org.junit.jupiter.params.provider.Arguments.of("2025-09-19T10:00:00+03:00[Europe/Chisinau]", "2025-09-19T10:30:00+03:00[Europe/Chisinau]", "2025-09-19T09:00:00+03:00[Europe/Chisinau]", false)
        );
    }

    @ParameterizedTest(name = "canJoin within window -> {3}")
    @MethodSource("joinWindowData")
    void canJoinParameterized(String s, String e, String now, boolean expected) {
        Meeting m = new Meeting("J", "Joinable", "org@example.com", zone(s), zone(e), "https://teams.microsoft.com/join/ok");
        assertEquals(expected, m.canJoin(zone(now), 5, 15));
    }

    @ParameterizedTest(name = "Valid email? \"{0}\"")
    @ValueSource(strings = {"ana.pop@example.com", "ion_99@example.co.uk", "a.b+1@sub.example.org"})
    void validEmail(String email) {
        assertTrue(Validators.isValidEmail(email));
    }

    @ParameterizedTest(name = "Invalid email? \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {"a@", "@example.com", "user@bad_domain", "no-tld@example"})
    void invalidEmail(String email) {
        assertFalse(Validators.isValidEmail(email));
    }
}

package com.endava.internship.meeting;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class MeetingTest {

    private static List<Meeting> meetings;

    private static ZonedDateTime zone(String iso) {
        return Validators.parseZdt(iso);
    }

    @BeforeAll
    static void initInstancies() {
        meetings = new ArrayList<>();
        meetings.add(new Meeting("M_Planning", "Sprint planning", "org@example.com",
                zone("2025-09-20T10:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-20T11:00:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/abc"));

        meetings.add(new Meeting("M_Daily", "Daily", "org@example.com",
                zone("2025-09-21T09:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-21T09:15:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/join/xyz"));

        meetings.add(new Meeting("M_Retro", "Retrospective", "org@example.com",
                zone("2025-09-24T17:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-24T18:00:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/123"));
    }

    @Test
    void junit_basicAssertions() {
        Meeting meeting = meetings.getFirst();
        assertEquals(60, meeting.durationMinutes());
        assertNotEquals(59, meeting.durationMinutes());

        String same = meeting.id();
        String sameRef = same;
        String different = new String(meeting.id());
        assertSame(same, sameRef);
        assertNotSame(same, different);

        assertTrue(Validators.isValidEmail("a.b+1@example.com"));
        assertFalse(Validators.isValidEmail("bad@"));

        assertNull(null);
        assertNotNull(meeting.subject());

        byte[] a = "ics".getBytes();
        byte[] b = "ics".getBytes();
        assertArrayEquals(a, b);

        List<Integer> l1 = List.of(1, 2, 3);
        List<Integer> l2 = List.of(1, 2, 3);
        assertIterableEquals(l1, l2);

        List<String> expected = List.of("Subject: .*", "Organizer: .*@.*", "Start: .*", "End: .*", "Join: https://teams\\.microsoft\\.com/.*");
        assertLinesMatch(expected, meeting.summaryLines());
    }

    @Test
    void junit_groupingAndExceptions() {
        Meeting m = meetings.get(1);

        assertAll("Meeting fields",
                () -> assertEquals("Daily", m.subject()),
                () -> assertTrue(m.durationMinutes() <= 30)
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Meeting("M2", "Bad", "org@", zone("2025-09-21T09:00:00+03:00[Europe/Chisinau]"), zone("2025-09-21T08:00:00+03:00[Europe/Chisinau]"), "bad-url")
        );
        assertTrue(ex.getMessage().length() > 0);

        assertDoesNotThrow(() -> m.reschedule(zone("2025-09-21T10:00:00+03:00[Europe/Chisinau]"), zone("2025-09-21T10:30:00+03:00[Europe/Chisinau]")));
        Object obj = m;
        Meeting casted = assertInstanceOf(Meeting.class, obj);
        assertEquals(m.id(), casted.id());
    }

    @Test
    void junit_timeouts_and_assumptions() {
        assertTimeout(Duration.ofMillis(50), () -> {
            var times = Recurrence.weekly(zone("2025-09-22T10:00:00+03:00[Europe/Chisinau]"), 3);
            assertEquals(3, times.size());
        });

        assertTimeoutPreemptively(Duration.ofMillis(50), () -> {
            Scheduler sch = new Scheduler();
            sch.schedule(new Meeting("A", "A", "org@example.com",
                    zone("2025-09-23T10:00:00+03:00[Europe/Chisinau]"),
                    zone("2025-09-23T10:30:00+03:00[Europe/Chisinau]"),
                    "https://teams.microsoft.com/join/1"));
        });

        Assumptions.assumeTrue(Locale.getDefault().getLanguage().length() > 0, "env sanity");
    }

    @Test
    void hamcrest_coreMatchers_and_custom() {
        Meeting m1 = meetings.getLast();

        assertThat(m1.joinUrl(), startsWith("https://teams.microsoft.com/"));
        assertThat(m1.subject(), allOf(containsString("Retro"), endsWith("spective")));

        m1.addAttendee(new Participant("Ana", "ana@example.com"));
        m1.addAttendee(new Participant("Ion", "ion@example.com"));
        assertThat(m1.attendees(), hasSize(2));
        assertThat(m1.attendees().stream().map(Participant::name).toList(), containsInAnyOrder("Ana", "Ion"));

        java.util.Map<String, String> meta = java.util.Map.of("platform", "Teams", "type", "online");
        assertThat(meta, hasKey("platform"));
        assertThat(meta, hasEntry("type", "online"));
    }

}

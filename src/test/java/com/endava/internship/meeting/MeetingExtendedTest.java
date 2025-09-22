package com.endava.internship.meeting;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class MeetingExtendedTest {

    private static List<Meeting> meetings;

    private static List<Participant> participants;

    private static ZonedDateTime zone(String iso) {
        return Validators.parseZdt(iso);
    }

    @BeforeAll
    static void testData() {
        meetings = new ArrayList<>();
        meetings.add(new Meeting("M_Planning", "Sprint planning", "org@example.com",
                zone("2025-09-22T14:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/abc"));

        meetings.add(new Meeting("M_Daily", "Daily", "org@example.com",
                zone("2025-09-23T15:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-23T16:15:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/join/extend"));

        meetings.add(new Meeting("M_Retro", "Retrospective", "org@example.com",
                zone("2025-09-23T15:29:00+03:00[Europe/Chisinau]"),
                zone("2025-09-23T16:00:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/123"));

        participants = new ArrayList<>();
        participants.add(new Participant("Ana", "ana@endava.com"));
        participants.add(new Participant("Jhon", "jhon@endava.com"));
    }

    @Test
    @DisplayName("JUnit Basic Assertions")
    void junit_basicAssertions() {
        // testMeetingCreation
        assertNotNull(meetings.getFirst());

        // attendeesChangeTest
        Meeting meeting = meetings.getFirst();
        meeting.addAttendee(participants.getFirst());
        assertEquals(1, meeting.attendees().size(), "Should be one attendee");

        meeting.addAttendee(participants.getLast());
        assertEquals(2, meeting.attendees().size(), "Should be two attendees");

        // meetingDurationTest
        long duration = meetings.getLast().durationMinutes();
        assertEquals(31L, duration, "Duration should be 90 minutes");

        // meetingOvelapTest
        boolean overlap = meetings.getLast().overlaps(meetings.get(1));
        assertTrue(overlap, "Overlap should be true");

        // meetingStatusTest

        // removeAttendeeByEmailTest

        // canJoinTest

    }

    @Test
    void junit_groupingAndExceptions() {
        Meeting m = meetings.get(1);
        m.addAttendee(participants.get(0));
        m.addAttendee(participants.get(1));

        assertAll("Meeting fields",
                () -> assertEquals("Daily", m.subject()),
                () -> assertTrue(m.durationMinutes() <= 75),
                () -> assertEquals(Meeting.Status.SCHEDULED, m.status()),
                () -> assertEquals(2, m.attendees().size())
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Meeting("M2", "Bad", "org@",
                        zone("2025-09-21T09:00:00+03:00[Europe/Chisinau]"),
                        zone("2025-09-21T08:00:00+03:00[Europe/Chisinau]"),
                        "bad-url")
        );
        assertFalse(ex.getMessage().isEmpty());

        assertDoesNotThrow(() -> m.reschedule(
                zone("2025-09-21T10:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-21T10:30:00+03:00[Europe/Chisinau]")));
    }

    @RepeatedTest(2)
    void junit_timeouts_and_assumptions() {
        assertTimeout(Duration.ofMillis(10), () -> {
            List<ZonedDateTime> times = Recurrence.weekly(zone("2025-09-22T10:00:00+03:00[Europe/Chisinau]"), 3);
            assertEquals(3, times.size());
        });

        assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
            Scheduler sch = new Scheduler();
            sch.schedule(new Meeting("A", "A", "org@example.com",
                    zone("2025-09-23T10:00:00+03:00[Europe/Chisinau]"),
                    zone("2025-09-23T10:30:00+03:00[Europe/Chisinau]"),
                    "https://teams.microsoft.com/join/1"));
        });

        Assumptions.assumeTrue(System.getProperty("os.name").contains("Windows"));
        Assumptions.assumeTrue(!Locale.getDefault().getLanguage().isEmpty(), "env sanity");
    }

    @Test
    void hamcrest_coreMatchers_and_custom() {
        Meeting meeting = meetings.getLast();

        assertThat(meeting.joinUrl(), startsWith("https://teams.microsoft.com/"));
        assertThat(meeting.subject(), allOf(containsString("Retro"), endsWith("spective")));

        meeting.addAttendee(participants.getFirst());
        meeting.addAttendee(participants.getLast());
        assertThat(meeting.attendees(), hasSize(2));

        List<String> names = meeting.attendees().stream().map(Participant::name).toList();
        assertThat(names, containsInAnyOrder("Ana", "Jhon"));

        Map<String, String> meta = Map.of("platform", "Teams", "type", "online");
        assertThat(meta, hasKey("platform"));
        assertThat(meta, hasEntry("type", "online"));
    }

}

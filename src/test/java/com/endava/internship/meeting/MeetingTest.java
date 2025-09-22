package com.endava.internship.meeting;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MeetingTest {

    private static ZonedDateTime zone(String iso) {
        return Validators.parseZdt(iso);
    }

    @Test
    public void testMeetingCreation() {
        Meeting meeting = new Meeting("Presentation",
                "Junit",
                "sergiu.creciun@endava.com",
                zone("2025-09-22T14:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/junit");

        assertNotNull(meeting);
    }

    @Test
    public void meetingWithInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Meeting("Presentation",
                    "Junit",
                    "sergiu.creciun@endava@com",
                    zone("2025-09-22T14:00:00+03:00[Europe/Chisinau]"),
                    zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                    "https://teams.microsoft.com/l/meetup-join/junit");
        });
    }

    @Test
    public void meetingWithInvalidUrl() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Meeting("Presentation",
                    "Junit",
                    "sergiu.creciun@endava.com",
                    zone("2025-09-22T14:00:00+03:00[Europe/Chisinau]"),
                    zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                    "https://microsoft.com/l/meetup-join/junit");
        });
    }

    @Test
    public void meetingWithInvalidTimeIntersected() {
        assertThrows(DateTimeException.class, () -> {
            new Meeting("Presentation",
                    "Junit",
                    "sergiu.creciun@endava.com",
                    zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                    zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                    "https://teams.microsoft.com/l/meetup-join/junit");
        });
    }

    @Test
    public void attendeesChangeTest() {
        Meeting meeting = new Meeting("Presentation",
                "Junit",
                "sergiu.creciun@endava.com",
                zone("2025-09-22T14:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/junit");
        Participant participant1 = new Participant("Jhon Doe", "jhob.doe@endava.com");
        meeting.addAttendee(participant1);
        assertEquals(1, meeting.attendees().size(), "Should be one attendee");

        Participant participant2 = new Participant("Robert Anderson", "robert.abc@endava.com");
        meeting.addAttendee(participant2);
        assertEquals(2, meeting.attendees().size(), "Checking if the organizer was added as attendee too");

        Participant participant3 = new Participant("Robert Anderson", "robert.abc@endava.com");
        assertThrows(UnsupportedOperationException.class, () -> {
            List<Participant> participants = meeting.attendees();
            participants.add(participant3);
        });

    }

    @Test
    public void meetingDurationTest() {
        Meeting meeting = new Meeting("Presentation",
                "Junit",
                "sergiu.creciun@endava.com",
                zone("2025-09-22T14:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/junit");

        assertEquals(90L, meeting.durationMinutes(), "Meeting duration should be 90 minutes");
    }

    @Test
    public void meetingOvelapTest() {
        Meeting meeting1 = new Meeting("Presentation",
                "Junit",
                "sergiu.creciun@endava.com",
                zone("2025-09-22T14:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/junit");

        Meeting meeting2 = new Meeting("Presentation",
                "Junit",
                "sergiu.creciun@endava.com",
                zone("2025-09-22T15:30:00+03:00[Europe/Chisinau]"),
                zone("2025-09-22T16:00:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/junit");

        assertFalse(meeting1.overlaps(meeting2));

        Meeting meeting3 = new Meeting("Presentation",
                "Junit",
                "sergiu.creciun@endava.com",
                zone("2025-09-22T15:29:00+03:00[Europe/Chisinau]"),
                zone("2025-09-22T16:00:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/junit");

        assertTrue(meeting1.overlaps(meeting3));
    }

    @Test
    public void meetingStatusTest() {
        Meeting meeting = new Meeting("Presentation",
                "Junit",
                "sergiu.creciun@endava.com",
                zone("2025-09-22T15:29:00+03:00[Europe/Chisinau]"),
                zone("2025-09-22T16:00:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/junit");
        Participant participant = new Participant("Jhon Doe", "jhob.doe@endava.com");
        meeting.addAttendee(participant);
        assertEquals(Meeting.Status.SCHEDULED, meeting.status(), "Meeting should be scheduled");

        meeting.cancel();
        assertEquals(Meeting.Status.CANCELLED, meeting.status(), "Meeting should be canceled");
    }

    @Test
    public void removeAttendeeByEmailTest() {
        //...
    }

    @Test
    public void canJoinTest() {
        //...
    }

}

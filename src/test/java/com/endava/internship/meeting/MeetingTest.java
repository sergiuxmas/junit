package com.endava.internship.meeting;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Creates a valid meeting when all required data is provided")
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
    @DisplayName("Throws an exception when the organizer email is invalid")
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
    @DisplayName("Throws an exception when the meeting URL is not a Teams URL")
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
    @DisplayName("Throws an exception when start and end time are the same")
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
    @DisplayName("Allows attendees to be added and returns a read-only attendee list")
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
    @DisplayName("Calculates the meeting duration in minutes")
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
    @DisplayName("Detects when two meetings overlap in time")
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
    @DisplayName("Changes meeting status from scheduled to cancelled")
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
    @DisplayName("Removes an attendee by email address")
    public void removeAttendeeByEmailTest() {
        //...
    }

    @Test
    @DisplayName("Checks whether a participant can join the meeting")
    public void canJoinTest() {
        //...
    }

}

package com.endava.internship.meeting;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Meeting {
    public enum Status {SCHEDULED, CANCELLED}

    private final String id;
    private String subject;
    private final String organizerEmail;
    private final List<Participant> attendees = new ArrayList<>();
    private ZonedDateTime start;
    private ZonedDateTime end;
    private String joinUrl;
    private Status status = Status.SCHEDULED;

    public Meeting(String id, String subject, String organizerEmail, ZonedDateTime start, ZonedDateTime end, String joinUrl) {
        this.id = Objects.requireNonNull(id);
        this.subject = Objects.requireNonNull(subject);
        this.organizerEmail = Objects.requireNonNull(organizerEmail);
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.joinUrl = Objects.requireNonNull(joinUrl);
        validate();
    }

    private void validate() {
        if (!Validators.isValidEmail(organizerEmail)) throw new IllegalArgumentException("Invalid organizer email");
        if (!Validators.isValidTeamsLink(joinUrl)) throw new IllegalArgumentException("Invalid Teams URL");
        if (!start.isBefore(end)) throw new IllegalArgumentException("start must be before end");
    }

    public String id() {
        return id;
    }

    public String subject() {
        return subject;
    }

    public String organizerEmail() {
        return organizerEmail;
    }

    public ZonedDateTime start() {
        return start;
    }

    public ZonedDateTime end() {
        return end;
    }

    public String joinUrl() {
        return joinUrl;
    }

    public Status status() {
        return status;
    }

    public List<Participant> attendees() {
        return Collections.unmodifiableList(attendees);
    }

    public long durationMinutes() {
        return Duration.between(start, end).toMinutes();
    }

    public boolean overlaps(Meeting other) {
        return !(end.isEqual(other.start) || end.isBefore(other.start) || start.isEqual(other.end) || start.isAfter(other.end));
    }

    public void cancel() {
        this.status = Status.CANCELLED;
    }

    public void reschedule(ZonedDateTime newStart, ZonedDateTime newEnd) {
        if (!newStart.isBefore(newEnd)) throw new IllegalArgumentException("start must be before end");
        this.start = newStart;
        this.end = newEnd;
    }

    public void addAttendee(Participant p) {
        if (p == null || !Validators.isValidEmail(p.email())) throw new IllegalArgumentException("Invalid participant");
        attendees.add(p);
    }

    public boolean removeAttendeeByEmail(String email) {
        return attendees.removeIf(a -> a.email().equalsIgnoreCase(email));
    }

    public boolean canJoin(ZonedDateTime now, int minutesBefore, int minutesAfter) {
        ZonedDateTime open = start.minusMinutes(minutesBefore);
        ZonedDateTime close = end.plusMinutes(minutesAfter);
        return !now.isBefore(open) && !now.isAfter(close) && status == Status.SCHEDULED;
    }

    public List<String> summaryLines() {
        List<String> lines = new ArrayList<>();
        lines.add("Subject: " + subject);
        lines.add("Organizer: " + organizerEmail);
        lines.add("Start: " + start);
        lines.add("End: " + end);
        lines.add("Join: " + joinUrl);
        return lines;
    }
}

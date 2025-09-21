package com.endava.internship.meeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;

public final class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static ZonedDateTime zone(String iso) {
        return Validators.parseZdt(iso);
    }

    public static void main(String[] args) {
        log.info("== Teams / Online Meetings Demo ==");

        Scheduler scheduler = new Scheduler();
        Meeting mPlanning = new Meeting(
                "M_Planning",
                "Sprint Planning",
                "organizer@example.com",
                zone("2025-09-20T10:00:00+03:00[Europe/Chisinau]"),
                zone("2025-09-20T11:00:00+03:00[Europe/Chisinau]"),
                "https://teams.microsoft.com/l/meetup-join/abc"
        );
        mPlanning.addAttendee(new Participant("Ana", "ana@example.com"));
        mPlanning.addAttendee(new Participant("Ion", "ion@example.com"));

        scheduler.schedule(mPlanning);
        log.info("Scheduled {} '{}' ({} min)", mPlanning.id(), mPlanning.subject(), mPlanning.durationMinutes());
        log.info("Attendees: {}", mPlanning.attendees());

        log.info("-- Summary --");
        mPlanning.summaryLines().forEach(log::info);

        ZonedDateTime now = zone("2025-09-20T10:03:00+03:00[Europe/Chisinau]");
        boolean canJoin = mPlanning.canJoin(now, 5, 15);
        log.info("Now: {} | canJoin: {}", now, canJoin);

        List<ZonedDateTime> next = Recurrence.weekly(mPlanning.start(), 3);
        log.info("Next occurrences:");
        next.forEach(dt -> log.info(" - " + dt));

        try {
            Meeting mDemo = new Meeting(
                    "M_Demo",
                    "Architecture Review",
                    "organizer@example.com",
                    zone("2025-09-20T10:30:00+03:00[Europe/Chisinau]"),
                    zone("2025-09-20T11:15:00+03:00[Europe/Chisinau]"),
                    "https://teams.microsoft.com/join/xyz"
            );
            scheduler.schedule(mDemo);
            log.info("Scheduled " + mDemo.id());
        } catch (IllegalStateException ex) {
            log.info("Overlap detected and prevented: {}", ex.getMessage());
        }

        mPlanning.reschedule(
                zone("2025-09-20T11:30:00+03:00[Europe/Chisinau]"),
                zone("2025-09-20T12:00:00+03:00[Europe/Chisinau]")
        );
        log.info("Rescheduled {} -> {} to {}", mPlanning.id(), mPlanning.start(), mPlanning.end());
    }
}

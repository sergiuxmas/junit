package com.endava.internship.meeting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Stores meetings and prevents overlaps
 */
public final class Scheduler {
    private final List<Meeting> meetings = new ArrayList<>();

    public Meeting schedule(Meeting m) {
        Objects.requireNonNull(m);
        for (Meeting existing : meetings) {
            if (existing.overlaps(m)) {
                throw new IllegalStateException("Overlapping meeting detected: " + existing.id());
            }
        }
        meetings.add(m);
        return m;
    }

    public List<Meeting> all() {
        return java.util.Collections.unmodifiableList(meetings);
    }
}
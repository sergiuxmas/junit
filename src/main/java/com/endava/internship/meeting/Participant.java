package com.endava.internship.meeting;

import java.util.Objects;

public final class Participant {
    private final String name;
    private final String email;

    public Participant(String name, String email) {
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant other = (Participant) o;
        // Equality based solely on email
        return email.equals(other.email);
    }

    @Override
    public int hashCode() {
        // Must align with equals: only email contributes
        return email.hashCode();
    }

    @Override
    public String toString() {
        return name + " <" + email + ">";
    }
}

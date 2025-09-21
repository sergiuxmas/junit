package com.endava.internship.meeting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParticipantTest {

    @Test
    public void validParticipantTest() {
        Participant participant1 = new Participant("Jhon Arias", "jhon.arias@gmail.com");
        assertEquals("Jhon Arias <jhon.arias@gmail.com>", participant1.toString());

        Participant participant2 = new Participant("", "");
        assertEquals(" <>", participant2.toString());
    }

    @Test
    public void invalidParticipantTest() {
        assertThrows(NullPointerException.class,
                () -> new Participant("Jhon Arias", null)
        );

        assertThrows(NullPointerException.class,
                () -> new Participant(null, "jhon.arias@gmail.com")
        );
    }
}

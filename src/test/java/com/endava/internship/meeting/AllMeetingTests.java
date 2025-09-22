package com.endava.internship.meeting;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        MeetingTest.class,
        MeetingExtendedTest.class,
        ParameterizedMeetingTest.class
})
public class AllMeetingTests {
    // This class remains empty, used only as a holder for the above annotations
}

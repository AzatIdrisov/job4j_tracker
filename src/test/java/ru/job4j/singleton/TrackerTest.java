package ru.job4j.singleton;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void testFirstTracker() {
        TrackerFirst tracker1 = TrackerFirst.INSTANCE;
        TrackerFirst tracker2 = TrackerFirst.INSTANCE;
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void testSecondTracker() {
        TrackerSecond tracker1 = TrackerSecond.getInstance();
        TrackerSecond tracker2 = TrackerSecond.getInstance();
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void testThirdTracker() {
        TrackerThird tracker1 = TrackerThird.getInstance();
        TrackerThird tracker2 = TrackerThird.getInstance();
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void testFourthTracker() {
        TrackerFourth tracker1 = TrackerFourth.getInstance();
        TrackerFourth tracker2 = TrackerFourth.getInstance();
        assertThat(tracker1, is(tracker2));
    }

}
package ru.job4j.singleton;

import org.junit.Test;
import ru.job4j.tracker.Tracker;

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
        Tracker tracker1 = TrackerSecond.getInstance();
        Tracker tracker2 = TrackerSecond.getInstance();
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void testThirdTracker() {
        Tracker tracker1 = TrackerThird.getInstance();
        Tracker tracker2 = TrackerThird.getInstance();
        assertThat(tracker1, is(tracker2));
    }

    @Test
    public void testFourthTracker() {
        Tracker tracker1 = TrackerFourth.getInstance();
        Tracker tracker2 = TrackerFourth.getInstance();
        assertThat(tracker1, is(tracker2));
    }

}
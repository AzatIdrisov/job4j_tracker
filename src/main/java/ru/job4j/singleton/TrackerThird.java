package ru.job4j.singleton;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class TrackerThird {

    private static final Tracker INSTANCE = new Tracker();

    private TrackerThird() {
    }

    public static Tracker getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        Tracker tracker = TrackerThird.getInstance();
    }
}
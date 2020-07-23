package ru.job4j.singleton;

import ru.job4j.tracker.Item;

public class TrackerThird {

    private static final TrackerThird INSTANCE = new TrackerThird();

    private TrackerThird() {
    }

    public static TrackerThird getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerThird tracker = TrackerThird.getInstance();
    }
}
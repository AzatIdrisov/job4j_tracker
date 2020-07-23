package ru.job4j.singleton;

import ru.job4j.tracker.Item;

public class TrackerFourth {

    private TrackerFourth() {
    }

    public static TrackerFourth getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static final class Holder {
        private static final TrackerFourth INSTANCE = new TrackerFourth();
    }

    public static void main(String[] args) {
        TrackerFourth tracker = TrackerFourth.getInstance();
    }
}
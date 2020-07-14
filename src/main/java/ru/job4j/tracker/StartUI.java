package ru.job4j.tracker;

public class StartUI {
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setName("First task");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        System.out.println(result.getName() + " - " + result.getId());
    }
}

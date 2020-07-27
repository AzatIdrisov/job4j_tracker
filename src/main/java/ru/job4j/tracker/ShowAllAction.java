package ru.job4j.tracker;

import java.util.ArrayList;

public class ShowAllAction implements UserAction {
    private final Output out;

    public ShowAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        ArrayList<Item> allItems = tracker.findAll();
        for (Item item: allItems) {
            out.println("Id: " + item.getId() + " Name: " + item.getName());
        }
        return true;
    }
}

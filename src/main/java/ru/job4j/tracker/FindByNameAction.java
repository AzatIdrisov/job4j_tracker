package ru.job4j.tracker;

import java.util.ArrayList;

public class FindByNameAction implements UserAction {
    private final Output out;

    public FindByNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Find by Name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter item name");
        ArrayList<Item> items = tracker.findByName(name);
        if (items.size() != 0) {
            for (Item item : items) {
                out.println("Id: " + item.getId() + " Name: " + item.getName());
            }
        } else {
            out.println("Item with such name not found");
        }
        return true;
    }
}

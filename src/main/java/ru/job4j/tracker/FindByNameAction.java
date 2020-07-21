package ru.job4j.tracker;

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
        Item[] items = tracker.findByName(name);
        if (items.length != 0) {
            for (int i = 0; i < items.length; i++) {
                out.println("Id: " + items[i].getId() + " Name: " + items[i].getName());
            }
        } else {
            out.println("Item with such name not found");
        }
        return true;
    }
}

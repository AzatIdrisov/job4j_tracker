package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find by Id ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter item Id");
        Item finded = tracker.findById(id);
        if (finded != null) {
            System.out.println("Id: " + finded.getId() + " Name: " + finded.getName());
        } else {
            System.out.println("Item not found");
        }
        return true;
    }
}

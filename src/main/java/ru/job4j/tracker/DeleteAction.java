package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter item Id");
        System.out.println(String.format("%s", tracker.delete(id) ? "Ssuccessfully deleted" : "Error! Id not found."));
        return true;
    }
}

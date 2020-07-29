package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Delete Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter item Id");
        out.println(String.format("%s", tracker.delete(id)
                ? "Ssuccessfully deleted" : "Error! Id not found."));
        return true;
    }
}

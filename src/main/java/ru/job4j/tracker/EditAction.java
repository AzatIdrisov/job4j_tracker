package ru.job4j.tracker;

public class EditAction implements UserAction {
    private final Output out;

    public EditAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Edit Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter item Id");
        String newName = input.askStr("Enter new name");
        Item newItem = new Item(newName);
        out.println(String.format("%s", tracker.replace(id, newItem)
                ? "Ssuccessfully edit" : "Error! Id not found."));
        return true;
    }
}

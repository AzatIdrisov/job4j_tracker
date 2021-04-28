package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    private final Output out;

    public FindByIdAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Find by Id ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        int id = input.askInt("Enter item Id");
        Item finded = tracker.findById(String.valueOf(id));
        if (finded != null) {
            out.println("Id: " + finded.getId() + " Name: " + finded.getName());
        } else {
            out.println("Item not found");
        }
        return true;
    }
}

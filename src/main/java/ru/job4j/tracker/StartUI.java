package ru.job4j.tracker;

public class StartUI {
    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select:");
            if (select == 0) {
                createItem(input, tracker);
            } else if (select == 1) {
                showAll(tracker);
            } else if (select == 2) {
                edit(input, tracker);
            } else if (select == 3) {
                delete(input, tracker);
            } else if (select == 4) {
                FindById(input, tracker);
            } else if (select == 5) {
                FindByName(input, tracker);
            } else if (select == 6) {
                run = false;
                System.out.println("Good Bye!");
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }

    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void showAll(Tracker tracker) {
        Item[] allItems = tracker.findAll();
        for (int i = 0; i < allItems.length; i++) {
            System.out.println("Id: " + allItems[i].getId() + " Name: " + allItems[i].getName());
        }
    }

    public static void edit(Input input, Tracker tracker) {
        int id = input.askInt("Enter item Id");
        String newName = input.askStr("Enter new name");
        Item newItem = new Item(newName);
        System.out.println(String.format("%s", tracker.replace(id, newItem) ? "Ssuccessfully edit" : "Error! Id not found."));
    }

    public static void delete(Input input, Tracker tracker) {
        int id = input.askInt("Enter item Id");
        System.out.println(String.format("%s", tracker.delete(id) ? "Ssuccessfully deleted" : "Error! Id not found."));
    }

    public static void FindById(Input input, Tracker tracker) {
        int id = input.askInt("Enter item Id");
        Item finded = tracker.findById(id);
        if (finded != null) {
            System.out.println("Id: " + finded.getId() + " Name: " + finded.getName());
        } else {
            System.out.println("Item not found");
        }
    }

    public static void FindByName(Input input, Tracker tracker) {
        String name = input.askStr("Enter item name");
        Item[] items = tracker.findByName(name);
        if (items.length != 0) {
            for (int i = 0; i < items.length; i++) {
                System.out.println("Id: " + items[i].getId() + " Name: " + items[i].getName());
            }
        } else {
            System.out.println("Item with such name not found");
        }
    }
}

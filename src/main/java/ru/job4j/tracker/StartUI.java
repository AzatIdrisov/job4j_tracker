package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {
    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = Integer.valueOf(scanner.nextLine());
            if (select == 0) {
                System.out.println("=== Create a new Item ====");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.add(item);
            } else if (select == 1) {
                Item[] allItems = tracker.findAll();
                for (int i = 0; i < allItems.length; i++) {
                    System.out.println("Id: " + allItems[i].getId() + " Name: " + allItems[i].getName());
                }
            } else if (select == 2) {
                System.out.println("Enter item Id");
                int id = Integer.valueOf(scanner.nextLine());
                System.out.println("Enter new name");
                String newName = scanner.nextLine();
                Item newItem = new Item(newName);
                System.out.println(String.format("%s", tracker.replace(id, newItem) ? "Ssuccessfully edit" : "Error! Id not found."));
            } else if (select == 3) {
                System.out.println("Enter item Id");
                int id = Integer.valueOf(scanner.nextLine());
                System.out.println(String.format("%s", tracker.delete(id) ? "Ssuccessfully deleted" : "Error! Id not found."));
            } else if (select == 4) {
                System.out.println("Enter item Id");
                int id = Integer.valueOf(scanner.nextLine());
                Item finded = tracker.findById(id);
                if (finded!=null) {
                    System.out.println("Id: " + finded.getId() + " Name: " + finded.getName());
                } else {
                    System.out.println("Item not found");
                }
            } else if (select == 5) {
                System.out.println("Enter item name");
                String name = scanner.nextLine();
                Item[] items = tracker.findByName(name);
                if (items.length != 0) {
                    for (int i = 0; i < items.length; i++) {
                        System.out.println("Id: " + items[i].getId() + " Name: " + items[i].getName());
                    }
                } else {
                    System.out.println("Item with such name not found");
                }

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
        System.out.println("Select:");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}

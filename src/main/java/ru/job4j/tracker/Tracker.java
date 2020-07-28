package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public List<Item> findAll() {
        return items;
    }

    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> itemsFindByKey = new ArrayList<>();
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                itemsFindByKey.add(item);
            }
        }
        return itemsFindByKey;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
            items.add(index, item);
            items.get(index).setId(id);
        }
        return index != -1;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
        }
        return index != -1;
    }
}
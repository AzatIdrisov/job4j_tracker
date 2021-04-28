package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemTracker implements Store {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    @Override
    public void init() {

    }

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    @Override
    public Item findById(String id) {
        int index = indexOf(Integer.parseInt(id));
        return index != -1 ? items.get(index) : null;
    }

    @Override
    public List<Item> findAll() {
        return items;
    }

    @Override
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> itemsFindByKey = new ArrayList<>();
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                itemsFindByKey.add(item);
            }
        }
        return itemsFindByKey;
    }

    @Override
    public boolean replace(String id, Item item) {
        int index = indexOf(Integer.parseInt(id));
        if (index != -1) {
            item.setId(Integer.parseInt(id));
            items.set(index, item);
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

    @Override
    public boolean delete(String id) {
        int index = indexOf(Integer.parseInt(id));
        if (index != -1) {
            items.remove(index);
        }
        return index != -1;
    }

    @Override
    public void close() throws Exception {

    }
}
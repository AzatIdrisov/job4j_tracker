package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SortedItemTest {
    @Test
    public void whenSortedToHigh() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Fix bug"));
        items.add(new Item("Reboot server"));
        items.add(new Item("Drink coffee"));
        Collections.sort(items, new AscendingSortedItem());
        assertThat(items.get(0).getName(), is("Drink coffee"));
    }

    @Test
    public void whenSortedToLow() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Fix bug"));
        items.add(new Item("Reboot server"));
        items.add(new Item("Drink coffee"));
        Collections.sort(items, new DescendingSortedItem());
        assertThat(items.get(0).getName(), is("Reboot server"));
    }
}
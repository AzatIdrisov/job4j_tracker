package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

public class SubList {
    public static List<String> getElementsBetweenIndexes(List<String> list, String el) {
        List<String> rsl = new ArrayList<>();
        int start = list.indexOf(el);
        int last = list.lastIndexOf(el);
        if (start != -1 && last != start) {
            rsl.addAll(list.subList(start, last));
        }
        return rsl;
    }
}
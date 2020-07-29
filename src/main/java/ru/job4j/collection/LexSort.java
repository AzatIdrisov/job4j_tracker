package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int leffSeparator = left.indexOf('.');
        int rightSeparator = right.indexOf('.');
        String first = left.substring(0, leffSeparator);
        String second = right.substring(0, rightSeparator);
        return Integer.compare(Integer.parseInt(first), Integer.parseInt(second));
    }
}
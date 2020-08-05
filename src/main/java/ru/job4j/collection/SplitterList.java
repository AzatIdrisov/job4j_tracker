package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

public class SplitterList {
    public static List<String> split(List<String> left, List<String> middle, List<String> right) {
        List<String> list = new ArrayList<>();
        list.addAll(left);
        list.retainAll(middle);
        list.removeAll(right);
        return list;
    }
}

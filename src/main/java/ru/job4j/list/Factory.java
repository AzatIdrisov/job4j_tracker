package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("first", "second", "third", "fourth", "fifth"));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
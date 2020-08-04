package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchAtt {
    public static List<Attachment> filter(List<Attachment> list, Predicate<Attachment> predicate) {
        List<Attachment> rsl = new ArrayList<>();
        for (Attachment att : list) {
            if (predicate.test(att)) {
                rsl.add(att);
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<Attachment> rsl = List.of(
                new Attachment("first", 90),
                new Attachment("bug", 110),
                new Attachment("third", 150)
        );
        System.out.println(filter(rsl, x -> x.getSize() > 100 && x.getName().equals("bug")));
    }
}
package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamEx {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        for (int i = -10; i < 10; i++) {
            nums.add(i);
        }
        nums = nums.stream().filter(num -> num > 0).collect(Collectors.toList());
        nums.forEach(System.out::println);
    }
}

package ru.job4j.collection;

import java.util.*;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean result = true;
        Map<Character, Integer> lettersLeft = new HashMap<>();
        for (char letter : left.toCharArray()) {
            lettersLeft.put(letter, lettersLeft.getOrDefault(letter, 0) + 1);
        }
        Map<Character, Integer> lettersRight = new HashMap<>();
        for (char letter : right.toCharArray()) {
            lettersRight.put(letter, lettersRight.getOrDefault(letter, 0) + 1);
        }
        for (Character letter : lettersRight.keySet()) {
            if (!lettersLeft.containsKey(letter) || !(lettersLeft.get(letter).equals(lettersRight.get(letter)))) {
                result = false;
                break;
            }
        }
        return result;
    }
}

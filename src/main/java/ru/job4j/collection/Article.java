package ru.job4j.collection;


import java.util.HashSet;
import java.util.Set;

public class Article {
    public static boolean generateBy(String origin, String line) {
        boolean result = true;
        String[] words = origin.split(" ");
        String[] text = line.split(" ");
        Set<String> uniqueWords = new HashSet<>();
        for (String originWord : words) {
            uniqueWords.add(originWord.replaceAll("\\p{P}", ""));
        }
        for (String checkingWord : text) {
            if (!uniqueWords.contains(checkingWord)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
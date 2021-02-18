package ru.job4j.collection;


import java.util.HashMap;
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

    public static boolean generateByWithHashMap(String origin, String line) {
        boolean result = true;
        String[] words = origin.split(" ");
        String[] text = line.split(" ");
        HashMap<String, Integer> wordsFrequency = new HashMap<>();
        for (String originWord : words) {
            if (wordsFrequency.containsKey(originWord)) {
                wordsFrequency.put(originWord.replaceAll("\\p{P}", ""),
                        wordsFrequency.get(originWord) + 1);
            } else {
                wordsFrequency.put(originWord.replaceAll("\\p{P}", ""), 1);
            }
        }
        for (String checkingWord : text) {
            if (!wordsFrequency.containsKey(checkingWord)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
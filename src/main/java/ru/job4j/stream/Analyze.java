package ru.job4j.stream;

import java.nio.charset.IllegalCharsetNameException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {

    public static double averageScore(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.getSubjects().stream())
                .mapToDouble(Subject::getScore).average().getAsDouble();
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.getName(),
                pupil.getSubjects().stream()
                        .mapToInt(Subject::getScore).average().getAsDouble()))
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        Map<String, Double> subjects = stream.flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        Collectors.averagingDouble(Subject::getScore)));
        return subjects.entrySet()
                .stream()
                .map(subject -> new Tuple(subject.getKey(), subject.getValue()))
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.getName(),
                pupil.getSubjects().stream()
                        .mapToInt(Subject::getScore).sum()))
                .max(Comparator.comparingDouble(Tuple::getScore)).get();
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        Map<String, Integer> subjects = stream.flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        Collectors.summingInt(Subject::getScore)));
        return subjects.entrySet().stream()
                .map(subject -> new Tuple(subject.getKey(), subject.getValue()))
                .max((first, second) -> (int) (first.getScore() - second.getScore())).get();
    }
}
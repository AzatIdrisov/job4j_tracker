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
        Map<String, Integer> subCount = new HashMap<>();
        Map<String, Integer> subScore = new HashMap<>();
        List<Subject> subjects = stream.flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.toList());
        Iterator<Subject> iterator = subjects.iterator();
        while (iterator.hasNext()) {
            Subject subject = iterator.next();
            if (subCount.containsKey(subject.getName())) {
                subCount.put(subject.getName(), subCount.get(subject.getName()) + 1);
                subScore.put(subject.getName(),
                        subScore.get(subject.getName()) + subject.getScore());
            } else {
                subCount.put(subject.getName(), 1);
                subScore.put(subject.getName(), subject.getScore());
            }
        }
        return subScore.keySet().stream()
                .map(subject -> new Tuple(subject, subScore.get(subject) / subCount.get(subject)))
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.getName(),
                pupil.getSubjects().stream()
                        .mapToInt(Subject::getScore).sum()))
                .max(Comparator.comparingDouble(Tuple::getScore)).get();
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        Map<String, Integer> subScore = new HashMap<>();
        List<Subject> subjects = stream.flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.toList());
        Iterator<Subject> iterator = subjects.iterator();
        while (iterator.hasNext()) {
            Subject subject = iterator.next();
            if (subScore.containsKey(subject.getName())) {
                subScore.put(subject.getName(),
                        subScore.get(subject.getName()) + subject.getScore());
            } else {
                subScore.put(subject.getName(), subject.getScore());
            }
        }
        return subScore.keySet().stream()
                .map(subject -> new Tuple(subject, subScore.get(subject)))
                .max((first, second) -> (int) (first.getScore() - second.getScore())).get();
    }
}
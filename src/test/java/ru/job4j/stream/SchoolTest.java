package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SchoolTest {

    @Test
    public void whenGetAClass() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Petr", 80));
        students.add(new Student("Azat", 90));
        students.add(new Student("Stepan", 60));
        students.add(new Student("Vasya", 45));
        List<Student> result = School.collect(students, student -> student.getScore() >= 70);
        List<Student> expected = List.of(new Student("Petr", 80), new Student("Azat", 90));
        assertThat(result, is(expected));
    }

    @Test
    public void whenGetBClass() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Petr", 80));
        students.add(new Student("Azat", 90));
        students.add(new Student("Stepan", 60));
        students.add(new Student("Vasya", 45));
        List<Student> result = School.collect(students, student -> student.getScore() >= 50
                && student.getScore() < 70);
        List<Student> expected = List.of(new Student("Stepan", 60));
        assertThat(result, is(expected));
    }

    @Test
    public void whenGetCClass() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Petr", 80));
        students.add(new Student("Azat", 90));
        students.add(new Student("Stepan", 60));
        students.add(new Student("Vasya", 45));
        List<Student> result = School.collect(students, student -> student.getScore() < 50);
        List<Student> expected = List.of(new Student("Vasya", 45));
        assertThat(result, is(expected));
    }
}
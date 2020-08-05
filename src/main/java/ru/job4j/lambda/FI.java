package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class FI {
    public static void main(String[] args) {
        Attachment[] atts = {
                new Attachment("image 1", 20),
                new Attachment("image 3", 120),
                new Attachment("image 0", 23)
        };
        Comparator<Attachment> comparator = (left, right) -> left.getSize() - right.getSize();
        Comparator<Attachment> compName = (left, right) -> {
            return left.getName().compareTo(right.getName());
        };
        Comparator<Attachment> compLen = (left, right) -> {
            System.out.println("compare - " + right.getName().length()
                    + " : " + left.getName().length());
            return right.getName().length() - left.getName().length();
        };
        Arrays.sort(atts, compLen);
        for (Attachment att : atts) {
            System.out.println(att);
        }
    }

}
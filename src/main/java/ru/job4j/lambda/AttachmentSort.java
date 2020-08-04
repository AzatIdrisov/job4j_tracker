package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AttachmentSort {
    public static void main(String[] args) {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("image 1", 100),
                new Attachment("image 2", 34),
                new Attachment("image 3", 13)
        );
        Comparator comparator =  new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Attachment left = (Attachment) o1;
                Attachment right = (Attachment) o2;
                return left.getSize() - right.getSize();
            }
        };

        Comparator<Attachment> compNames = new Comparator<Attachment>() {
            @Override
            public int compare(Attachment o1, Attachment o2) {
                int rsl = 0;
                int size = Math.min(o1.getName().length(), o2.getName().length());
                for (int i = 0; i < size; i++) {
                    rsl = Character.compare(o1.getName().charAt(i), o2.getName().charAt(i));
                    if (rsl != 0) {
                        break;
                    }
                }
                if (rsl == 0 && o1.getName().length() != o2.getName().length()) {
                    rsl = Integer.compare(o1.getName().length(), o2.getName().length());
                }
                return rsl;
            }
        };
        attachments.sort(comparator);
        System.out.println(attachments);
    }
}
package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Azat");
        student.setSurname("Idrisov");
        student.setGroup("ENM-190102");
        student.setAdmissionDate(new Date());

        System.out.println("Student info : "+student.getName()+" "+student.getSurname()+" "+student.getGroup()+ "; Admission Date : "+ student.getAdmissionDate());
    }
}

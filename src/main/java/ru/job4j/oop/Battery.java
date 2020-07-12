package ru.job4j.oop;

public class Battery {
    private int load;

    public void exchange(Battery another) {
        this.load -= 5;
        another.load += 5;
    }

}

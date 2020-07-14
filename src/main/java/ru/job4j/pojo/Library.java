package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book cleanCode = new Book("Clean Code", 325);
        Book lotr = new Book("Lord of the Rings", 450);
        Book head = new Book("Head First", 900);
        Book hobbit = new Book("Hobbit", 600);

        Book[] books = new Book[4];
        books[0] = cleanCode;
        books[1] = lotr;
        books[2] = head;
        books[3] = hobbit;

        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + " - " + books[i].getPageNum());
        }
        System.out.println();
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (int i = 0; i < books.length; i++) {
            if(books[i].getName().equals("Clean Code")){
                System.out.println(books[i].getName() + " - " + books[i].getPageNum());
            }
        }
    }
}

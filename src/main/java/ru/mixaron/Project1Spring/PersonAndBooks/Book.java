package ru.mixaron.Project1Spring.PersonAndBooks;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    @NotEmpty(message = "Name Cant be empty")
    @Size(min = 2, max = 50, message = "Name cant be so small or so big")
    private String book_name;

    @NotEmpty(message = "Author Cant be empty")
    @Size(min = 2, max = 50, message = "Author cant be so small or so big")
    private String book_author;

    @Min(value = 0, message = "Min Year 0")
    @Max(value = 2023, message = "Max year 2023")
    private int year;

    private int book_id;
    public Book() {}

    public Book(String book_name, String book_author, int year, int book_id) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.year = year;
        this.book_id = book_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}

package ru.mixaron.Project1Spring.PersonAndBooks;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;


@Entity
@Table
public class Book {


    @Column(name = "book_name")
    @NotEmpty(message = "Name Cant be empty")
    @Size(min = 2, max = 50, message = "Name cant be so small or so big")
    private String bookName;


    @Column(name = "book_author")
    @NotEmpty(message = "Author Cant be empty")
    @Size(min = 2, max = 50, message = "Author cant be so small or so big")
    private String book_author;


    @Column(name = "year")
    @Min(value = 0, message = "Min Year 0")
    @Max(value = 2023, message = "Max year 2023")
    private int year;

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Transient
    private boolean dateNow = false;


    public Book() {}

    public Book(String book_name, String book_author, int year, int book_id) {
        this.bookName = book_name;
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
        return bookName;
    }

    public void setBook_name(String book_name) {
        this.bookName = book_name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDateNow() {
        return dateNow;
    }

    public void setDateNow(boolean dateNow) {
        this.dateNow = dateNow;
    }
}

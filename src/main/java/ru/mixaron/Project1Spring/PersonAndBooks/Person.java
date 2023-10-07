package ru.mixaron.Project1Spring.PersonAndBooks;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "person")
public class Person {

    @Column(name = "person_name")
    @NotEmpty(message = "Name Cant be empty")
    @Size(min = 2, max = 50, message = "Name cant be so small or so big")
    private String person_name;

    @Column(name = "person_bd")
    @Min(value = 1900, message = "Значение должно быть больше или равно 0")
    @Max(value = 2023, message = "Значение должно быть меньше или равно 100")
    private int person_bd;

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;


    public Person() {}

    public Person(String person_name, int person_bd, int person_id) {
        this.person_bd = person_bd;
        this.person_name = person_name;
        this.person_id = person_id;
    }

    public int getPerson_bd() {
        return person_bd;
    }

    public void setPerson_bd(int person_bd) {
        this.person_bd = person_bd;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

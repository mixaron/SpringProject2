package ru.mixaron.Project1Spring.PersonAndBooks;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    @NotEmpty(message = "Name Cant be empty")
    @Size(min = 2, max = 50, message = "Name cant be so small or so big")
    private String person_name;

    @Min(value = 1900, message = "Значение должно быть больше или равно 0")
    @Max(value = 2023, message = "Значение должно быть меньше или равно 100")
    private int person_bd;

    private int person_id;


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
}

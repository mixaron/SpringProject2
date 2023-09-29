package ru.mixaron.Project1Spring.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;

import java.util.List;

@Component
public class DaoFile {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public DaoFile(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void newPerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person(person_name, person_bd) VALUES(?,?)",
                person.getPerson_name(),person.getPerson_bd());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void update(int id, Person person) {
         jdbcTemplate.update("UPDATE Person SET person_name=?, person_bd=? WHERE person_id = ?",
                person.getPerson_name(), person.getPerson_bd(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }
}

package ru.mixaron.Project1Spring.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;

import java.util.List;
import java.util.Optional;

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

//    public boolean isBook(int id) {
//        Integer bookid = jdbcTemplate.queryForObject("SELECT book_id FROM book WHERE person_id=?", new Object[]{id}, Integer.class);
//        return bookid != null;
//    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    public List<Book> indexByBooks(int id) {
        return jdbcTemplate.query("SELECT book_name, book_author, year FROM book JOIN person USING(person_id) WHERE person_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void changedPerson(int id, int person_id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person_id, id);
    }

    public Optional<Person> showName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}

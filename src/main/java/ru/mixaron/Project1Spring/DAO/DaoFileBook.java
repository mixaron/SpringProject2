package ru.mixaron.Project1Spring.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;

import java.util.List;

@Component
public class DaoFileBook {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public DaoFileBook(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> indexBook() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void newPersonBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(book_name, book_author, year) VALUES(?,?,?)",
                book.getBook_name(),book.getBook_author(), book.getYear());
    }

    public Book showBook(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET book_name=?, book_author=?, year=? WHERE book_id = ?",
                book.getBook_name(), book.getBook_author(),  book.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }


    public boolean isPerson(int id) {
        Integer personId = jdbcTemplate.queryForObject("SELECT person_id FROM book JOIN person USING(person_id) WHERE book_id=?", new Object[]{id}, Integer.class);
        return personId != null;
    }


    public Person showPerson(int id) {
        return jdbcTemplate.query("SELECT person_name, person_bd, person_id FROM person JOIN book USING(person_id) WHERE book_id=?"
                        , new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Object clearPerson(int id) {
        jdbcTemplate.update("UPDATE book SET person_id = null WHERE book_id=?", id);
        return null;
    }
}

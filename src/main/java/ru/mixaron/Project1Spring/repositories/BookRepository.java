package ru.mixaron.Project1Spring.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;
import ru.mixaron.Project1Spring.services.BookService;
import ru.mixaron.Project1Spring.services.PersonService;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

//    @Modifying
//    @Query("UPDATE Book b SET b.person_id = null where b.book_id=?1")
//    void updatePersonNotNull(int id);
//
//    @Modifying
//    @Query("SELECT p.person_name, p.person_bd, p.person_id FROM Person p JOIN p.Books b WHERE b.book_id = :bookId")
//    List<Book> showPerson(int id);
//
//    @Modifying
//    @Query("UPDATE Book b set b.person_id=?2 where b.book_id=?1")
//    void changedPerson(int id, int person_id);
//
//    @Modifying
//    @Query("SELECT b.person_id  FROM Book b JOIN b.Person p WHERE b.book_id = :bookId")
//    Integer isPerson(int id);

    Page<Book> findAll(Pageable v1);
    List<Book> findAll(Sort var1);
    List<Book> findByBookNameStartingWith(String name);
}

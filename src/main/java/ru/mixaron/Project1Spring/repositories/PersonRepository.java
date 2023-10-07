package ru.mixaron.Project1Spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

//    Optional<Person> findByPerson_name(String name);

//    @Modifying
//    @Query("SELECT p.person_name, p.person_bd, p.person_id FROM Person p JOIN p.Books b WHERE b.book_id = :bookId")
//    Person showPerson(int id);
}

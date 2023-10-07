package ru.mixaron.Project1Spring.services;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;
import ru.mixaron.Project1Spring.repositories.PersonRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> index() {
        return personRepository.findAll();
    }

    public Person show(int id) {
        Optional<Person> person  = personRepository.findById(id);
        return person.orElse(null);
    }

    public void newPerson(Person person) {
        personRepository.save(person);
    }

    public void update(int id, Person updatePerson) {
        updatePerson.setPerson_id(id);
        personRepository.save(updatePerson);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }


//   public Optional<Person> showPerson(String name) {
//        return personRepository.findByPerson_name(name);
//   }
   public List<Book> indexByBooks(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            person.get().getBooks().forEach(book1 -> {if (book1.getCreatedAt() != null) {
                long miles = Math.abs(book1.getCreatedAt().getTime() - new Date().getTime());
                if (miles > 86400000) book1.setDateNow(true);
            }});
            return person.get().getBooks();
        }
        return Collections.emptyList();
   }

}

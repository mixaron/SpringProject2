package ru.mixaron.Project1Spring.services;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;
import ru.mixaron.Project1Spring.repositories.BookRepository;

import java.util.*;

@Service
@Transactional()
public class BookService {


    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> indexBook() {
        return bookRepository.findAll();
    }

    public void newPersonBook(Book book) {
        bookRepository.save(book);
    }

    public Book showBook(int id) {
        return (Book) bookRepository.findById(id).orElse(null);
    }

    public void updateBook(int id, Book updateBook) {
        updateBook.setBook_id(id);
        bookRepository.save(updateBook);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

//    public void clearPerson(int id) {
//        bookRepository.updatePersonNotNull(id);
//    }
//
//    public List<Book> indexByBooks(int id) {
//        return bookRepository.showPerson(id);
//    }
//
//    public void changePerson(int id, int person_id) {
//        bookRepository.changedPerson(id, person_id);
//    }
//
//    public Boolean isPerson(int id) {
//        Integer integer = bookRepository.isPerson(id);
//        return integer != null;
//    }

    public Object clearPerson(int id) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
        });
        return null;
    }
    public void assign(int id, Person person) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setCreatedAt(new Date());
            book.setOwner(person);
        });
    }

    public boolean isPerson(int bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        return book !=null && book.getOwner() != null;
    }

    public Person getBookOwner(int id) {
        return bookRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    public List<Book> pagination(int id1, int id2) {
        return bookRepository.findAll(PageRequest.of(id1, id2)).getContent();
    }
    public List<Book> sort() {
        return bookRepository.findAll(Sort.by("year"));
    }

    public List<Book> paginationWithSort(int id1, int id2) {
        return  bookRepository.findAll(PageRequest.of(id1, id2, Sort.by("year"))).getContent();
    }

    public List<Book> searchLike(String query) {
        List<Book> book = bookRepository.findByBookNameStartingWith(query);

        for (Book book1 : book) {
            if (book1.getCreatedAt() != null) {
                long miles = Math.abs(book1.getCreatedAt().getTime() - new Date().getTime());
                if (miles > 86400000) book1.setDateNow(true);
            }
        }
        return bookRepository.findByBookNameStartingWith(query);
    }


}

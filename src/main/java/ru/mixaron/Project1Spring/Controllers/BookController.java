package ru.mixaron.Project1Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mixaron.Project1Spring.DAO.DaoFile;
import ru.mixaron.Project1Spring.DAO.DaoFileBook;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;

@Controller
@RequestMapping("/books")
public class BookController {
    private final DaoFileBook daoFileBook;
    private final DaoFile daoFile;

    @Autowired
    BookController(DaoFileBook daoFileBook, DaoFile daoFile) {
        this.daoFileBook = daoFileBook;
        this.daoFile = daoFile;
    }


//    @GetMapping
//    public String goIndex() {
//        return "books/index";
//    }
    @GetMapping
    public String watch(Model model) {
        model.addAttribute("book", daoFileBook.indexBook());
        return "books/watchBook";
    }
    @GetMapping("/new")
    public String save(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String savePost(@ModelAttribute("book") Book book) {
        daoFileBook.newPersonBook(book);
        return "books/watchBook";
    }



    @GetMapping("/{id}")
    public String clearPerson(@PathVariable("id") int id, Model model) {
//        model.addAttribute("AuthorD", daoFileBook.clearPerson(id));
        model.addAttribute("book", daoFileBook.showBook(id));
        model.addAttribute("person", daoFileBook.showPerson(id));
        model.addAttribute("people", daoFile.index());
        model.addAttribute("person123", new Person());
        try {
            model.addAttribute("personId", daoFileBook.isPerson(id));
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("personId", false);
        }
        return "books/show";
    }
    @PostMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", daoFileBook.showBook(id));
        model.addAttribute("person", daoFileBook.showPerson(id));
        try {
            model.addAttribute("personId", daoFileBook.isPerson(id));
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("personId", false);
        }
        return "books/show";
    }

    @PostMapping("/{id}/add")
    public String add(@PathVariable("id") int id, Model model, @ModelAttribute("person123") Person person) {
        daoFile.changedPerson(id, person.getPerson_id());
        System.out.println(person.getPerson_id());
        model.addAttribute("book", daoFileBook.showBook(id));
        model.addAttribute("person", daoFileBook.showPerson(id));
        try {
            model.addAttribute("personId", daoFileBook.isPerson(id));
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("personId", false);
        }
        System.out.println(person.getPerson_id());
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("bookEdit", daoFileBook.showBook(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("editPerson") Book book) {
        daoFileBook.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        daoFileBook.deleteBook(id);
        return "redirect:/books";
    }
}

package ru.mixaron.Project1Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mixaron.Project1Spring.DAO.DaoFileBook;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;

@Controller
@RequestMapping("/books")
public class BookController {
    private final DaoFileBook daoFileBook;

    @Autowired
    BookController(DaoFileBook daoFileBook) {
        this.daoFileBook = daoFileBook;
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
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", daoFileBook.showBook(id));
        return "books/show";
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

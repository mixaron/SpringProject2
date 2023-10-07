package ru.mixaron.Project1Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;
import ru.mixaron.Project1Spring.services.BookService;
import ru.mixaron.Project1Spring.services.PersonService;

import javax.naming.Name;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }


//    @GetMapping
//    public String goIndex() {
//        return "books/index";
//    }
    @GetMapping
    public String watch(Model model, @RequestParam(name = "page", required = false) Integer id,
                        @RequestParam(name = "books_per_page",required = false) Integer id2,
                        @RequestParam(name = "sort_by_year", required = false) String isTrue) {
        if (id != null && id2 != null && isTrue == null) {
            model.addAttribute("book", bookService.pagination(id, id2));
        }
        else if (id == null || id2 == null && isTrue == "true") {
            model.addAttribute("book", bookService.sort());
        }
        else model.addAttribute("book", bookService.paginationWithSort(id, id2));

        return "books/watchBook";
    }


    @GetMapping("/new")
    public String save(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String savePost(@ModelAttribute("book") Book book) {
        bookService.newPersonBook(book);
        return "books/watchBook";
    }



    @GetMapping("/{id}")
    public String clearPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.showBook(id));
        model.addAttribute("person", bookService.getBookOwner(id));
        model.addAttribute("people", personService.index());
        model.addAttribute("person123", new Person());
            model.addAttribute("personId", bookService.isPerson(id));
        return "books/show";
    }
    @PostMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.showBook(id));
        model.addAttribute("person", bookService.getBookOwner(id));
            model.addAttribute("personId", bookService.isPerson(id));

        return "books/show";
    }

    @PostMapping("/{id}/add")
    public String add(@PathVariable("id") int id, Model model, @ModelAttribute("person123") Person person) {
        bookService.assign(id, person);
        model.addAttribute("book", bookService.showBook(id));
        model.addAttribute("person", bookService.getBookOwner(id));
        model.addAttribute("personId", bookService.isPerson(id));
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("bookEdit", bookService.showBook(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("editPerson") Book book) {
        bookService.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search() {
        return "books/search";
    }

    @PostMapping("/search")
    public String postSearch(Model model, @RequestParam(name = "query", required = false) String query) {
        model.addAttribute("book", bookService.searchLike(query));
        return "books/search";
    }
}

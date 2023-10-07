package ru.mixaron.Project1Spring.Controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mixaron.Project1Spring.PersonAndBooks.Book;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;
import ru.mixaron.Project1Spring.services.BookService;
import ru.mixaron.Project1Spring.services.PersonService;
import ru.mixaron.Project1Spring.util.PersonValidator;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;

    private final BookService bookService;

    private final PersonValidator personValidator;

    @Autowired
    PeopleController(PersonService personService, BookService bookService, PersonValidator personValidator) {
        this.personService = personService;
        this.bookService = bookService;
        this.personValidator = personValidator;
    }


    @GetMapping
    public String goIndex() {
        return "people/index";
    }

    @GetMapping("/watchpeople")
    public String watch(Model model) {
        model.addAttribute("person", personService.index());
        return "people/watchPeople";
    }
    @GetMapping("/new")
    public String save(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String savePost(@ModelAttribute("person") @Valid Person person, BindingResult bi) {
        personValidator.validate(person, bi);
        if (bi.hasErrors()) {
            return "people/new";
        }
        personService.newPerson(person);
        return "people/watchPeople";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personService.show(id));
            model.addAttribute("book", personService.indexByBooks(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("personEdit", personService.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("editPerson") @Valid Person person, BindingResult bi) {
        personValidator.validate(person, bi);
        if (bi.hasErrors()) {
            return "people/edit";
        }
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}

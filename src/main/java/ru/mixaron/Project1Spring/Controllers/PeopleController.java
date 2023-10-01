package ru.mixaron.Project1Spring.Controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.mixaron.Project1Spring.DAO.DaoFile;
import ru.mixaron.Project1Spring.DAO.DaoFileBook;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;
import ru.mixaron.Project1Spring.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final DaoFile daoFile;

    private  final DaoFileBook daoFileBook;

    private final PersonValidator personValidator;

    @Autowired
    PeopleController(DaoFile daoFile, DaoFileBook daoFileBook, PersonValidator personValidator) {
        this.daoFile = daoFile;
        this.daoFileBook = daoFileBook;
        this.personValidator = personValidator;
    }


    @GetMapping
    public String goIndex() {
        return "people/index";
    }
    @GetMapping("/watchpeople")
    public String watch(Model model) {
        model.addAttribute("person", daoFile.index());
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
        daoFile.newPerson(person);
        return "people/watchPeople";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", daoFile.show(id));
        try {
            model.addAttribute("book", daoFile.indexByBooks(id));
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("book", false);
//            model.addAttribute("books", null);
        }
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("personEdit", daoFile.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("editPerson") @Valid Person person, BindingResult bi) {
        personValidator.validate(person, bi);
        if (bi.hasErrors()) {
            return "people/edit";
        }
        daoFile.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        daoFile.delete(id);
        return "redirect:/people";
    }
}

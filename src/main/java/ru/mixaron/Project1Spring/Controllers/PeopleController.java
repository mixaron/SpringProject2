package ru.mixaron.Project1Spring.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mixaron.Project1Spring.DAO.DaoFile;
import ru.mixaron.Project1Spring.DAO.DaoFileBook;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final DaoFile daoFile;

    private  final DaoFileBook daoFileBook;

    @Autowired
    PeopleController(DaoFile daoFile, DaoFileBook daoFileBook) {
        this.daoFile = daoFile;
        this.daoFileBook = daoFileBook;
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
    public String savePost(@ModelAttribute("person") Person person) {
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
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("personEdit", daoFile.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("editPerson") Person person) {
        daoFile.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        daoFile.delete(id);
        return "redirect:/people";
    }
}

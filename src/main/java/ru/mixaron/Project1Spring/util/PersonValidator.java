package ru.mixaron.Project1Spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;
import ru.mixaron.Project1Spring.services.PersonService;

@Component
public class PersonValidator  implements Validator {

    private final PersonService personService;


    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;


//        if (personService.showName(person.getPerson_name()) != null)
//            errors.rejectValue("person_name", "", "This name not Unique");
    }
}

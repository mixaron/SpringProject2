package ru.mixaron.Project1Spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mixaron.Project1Spring.DAO.DaoFile;
import ru.mixaron.Project1Spring.PersonAndBooks.Person;

@Component
public class PersonValidator  implements Validator {

    private final DaoFile daoFile;


    @Autowired
    public PersonValidator(DaoFile daoFile) {
        this.daoFile = daoFile;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;


        if (daoFile.showName(person.getPerson_name()).isPresent())
            errors.rejectValue("person_name", "", "This name not Unique");
    }
}

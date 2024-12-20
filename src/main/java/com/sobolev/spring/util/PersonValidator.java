package com.sobolev.spring.util;

import com.sobolev.spring.dao.PersonDAO;
import com.sobolev.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * Проверяет, поддерживает ли валидатор класс.
     * @param aClass Класс для проверки.
     * @return true, если класс поддерживается.
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    /**
     * Основная логика валидации.
     * @param o Объект, который нужно проверить.
     * @param errors Объект для хранения ошибок валидации.
     */
    @Override
    public void validate(Object o, Errors errors) {
//        Person person = (Person) o;
//
//        // Проверка: email уже используется
//        if (personDAO.show(person.getEmail()).isPresent()) {
//            // Поле: email, код ошибки: "", сообщение: "This email is already in use"
//            errors.rejectValue("email", "", "This email is already in use");
//        }
//
//        // Проверка: имя начинается с заглавной буквы
//        if (person.getName() != null && !person.getName().isEmpty()) {
//            if (!Character.isUpperCase(person.getName().codePointAt(0))) {
//                errors.rejectValue("name", "", "Name should start with a capital letter");
//            }
//        } else {
//            errors.rejectValue("name", "", "Name should not be empty");
//        }
    }
}

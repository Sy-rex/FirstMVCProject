package com.sobolev.spring.dao;

import com.sobolev.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Tom",26,"Tom@email.com"));
        people.add(new Person(++PEOPLE_COUNT,"Jack",30,"Jack@email.com"));
        people.add(new Person(++PEOPLE_COUNT,"Bob",18,"Bob@email.com"));
        people.add(new Person(++PEOPLE_COUNT,"Mike",45,"Mike@email.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setEmail(person.getEmail());
        personToBeUpdated.setAge(person.getAge());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}

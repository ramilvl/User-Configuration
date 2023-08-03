package com.example.spring.service;

import com.example.spring.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService{
    static final List<Person> persons;

    static{
        persons = new ArrayList<>();
        persons.add(new Person(UUID.randomUUID().toString(), "Nuraddin", "Sadili"));
        persons.add(new Person(UUID.randomUUID().toString(), "Ali", "Mammadov"));
        persons.add(new Person(UUID.randomUUID().toString(), "Jamal", "Ismayil"));
    }

    @Override
    public List<Person> list() {
        return persons;
    }

    @Override
    public Person getById(String id) {
        Optional<Person> result = persons.stream()
                .filter(person -> person.getId().equals(id))
                .findAny();
        return result.orElse(new Person());
    }

    @Override
    public Person save(Person person) {
        if (person.getId() == null) {
            person.setId(UUID.randomUUID().toString());
            persons.add(person);

            return person;
        }


        Optional<Person> result = persons.stream()
                .filter(p -> p.getId().equals(person.getId()))
                .findAny();

        Person oldRecord = result.orElseThrow();

        oldRecord.setName(person.getName());
        oldRecord.setSurname(person.getSurname());


        return oldRecord;
    }

    @Override
    public void deleteById(String id) {
        Optional<Person> result = persons.stream()
                .filter(person -> person.getId().equals(id))
                .findAny();

        if (result.isPresent())
            persons.remove(result.get());
    }
}

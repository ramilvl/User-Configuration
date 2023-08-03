package com.example.spring.service;

import com.example.spring.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> list();
    Person getById(String id);

    Person save(Person person);

    void deleteById(String id);
}

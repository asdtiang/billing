package org.asdtiang.billing.service;

import org.asdtiang.billing.entity.Person;
import org.springframework.data.domain.Page;


public interface PersonService {
    Page<Person> findAll(int page, int size);

    Page<Person> findByNameLike(String name, int page, int size);

    Person findById(Integer id);

    Person insert(Person person);

    Person update(Person person);

    void deleteById(Integer id);

}

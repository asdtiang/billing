package org.asdtiang.billing.repository.custom;

import org.asdtiang.billing.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PersonRepositoryCustom {
    Page<Person> findByNameLike(String name, Pageable pageable);
}

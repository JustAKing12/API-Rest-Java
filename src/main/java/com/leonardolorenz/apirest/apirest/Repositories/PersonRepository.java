package com.leonardolorenz.apirest.apirest.Repositories;

import com.leonardolorenz.apirest.apirest.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    boolean existsByDocumento(String documento);

}

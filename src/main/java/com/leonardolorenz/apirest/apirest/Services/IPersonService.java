package com.leonardolorenz.apirest.apirest.Services;

import com.leonardolorenz.apirest.apirest.Models.Person;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface IPersonService {
    public Person savePerson(Person person);
    public List<Person> getAllPersons();

    public Person updatePerson(Long id, Person person);
    public Person getPersonById(Long id) throws Exception;
    public Person findPersonById(Long id);
    public ResponseEntity<HashMap<String, String>> deletePerson(Long id) throws Exception;
}

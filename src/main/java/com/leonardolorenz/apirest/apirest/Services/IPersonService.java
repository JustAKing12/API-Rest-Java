package com.leonardolorenz.apirest.apirest.Services;

import com.leonardolorenz.apirest.apirest.Models.Person;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface IPersonService {
     Person savePerson(Person person);
     List<Person> getAllPersons();

     Person updatePerson(Long id, Person person);
     Person getPersonById(Long id) throws Exception;
     Person findPersonById(Long id);
     ResponseEntity<HashMap<String, String>> deletePerson(Long id) throws Exception;
}

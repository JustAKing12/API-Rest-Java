package com.leonardolorenz.apirest.apirest.Services;

import com.leonardolorenz.apirest.apirest.Models.Person;
import com.leonardolorenz.apirest.apirest.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        Person personUpdate = personRepository.getById(id);
        personUpdate.setNombre(person.getNombre());
        return personRepository.save(personUpdate);
    }

    @Override
    public Person getPersonById(Long id) throws Exception {
        personRepository.findById(id).orElseThrow(()-> new Exception("Person not found"));
        Person person = new Person();
        person.setId(id);
        person.setNombre(personRepository.getById(id).getNombre());
        person.setApellido(personRepository.getById(id).getApellido());
        person.setDomicilio(personRepository.getById(id).getDomicilio());
        person.setDocumento(personRepository.getById(id).getDocumento());
        return person;
    }

    @Override
    public Person findPersonById(Long id) {
        personRepository.findById(id);
        return null;
    }

    @Override
    public String deletePerson(Long id) throws Exception {
        personRepository.findById(id).orElseThrow(()-> new Exception("Person not found"));
        personRepository.deleteById(id);
        return null;
    }
}

package com.leonardolorenz.apirest.apirest.Services;

import com.leonardolorenz.apirest.apirest.Models.Person;
import com.leonardolorenz.apirest.apirest.Repositories.PersonRepository;
import com.leonardolorenz.apirest.apirest.exceptions.PersonAlredyExistException;
import com.leonardolorenz.apirest.apirest.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;
    @Autowired //(Hago inyeccion de dependencias con constructor para + seguridad)
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person savePerson(Person person) {
        if (personRepository.existsById(person.getId())) throw new PersonAlredyExistException("La persona ya esta agregada en la base de datos.");
        if (personRepository.existsByDocumento(person.getDocumento())) throw new PersonAlredyExistException("Ya existe una persona con ese documento agregada en la base de datos.");
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPersons() {
        if (personRepository.findAll().isEmpty()){

        }
        return personRepository.findAll();
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("No existe una persona con ese ID en la base de datos."));
        Person personUpdate = new Person();
        personUpdate.setId(id);
        personUpdate.setNombre(person.getNombre());
        personUpdate.setApellido(person.getApellido());
        personUpdate.setDocumento(person.getDocumento());
        personUpdate.setDomicilio(person.getDomicilio());
        personRepository.save(personUpdate);
        return personUpdate;
    }

    @Override
    public Person getPersonById(Long id) {
        personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("No existe una persona con ese ID en la base de datos."));
        Person person = new Person();
        person.setId(id);
        person.setNombre(personRepository.getReferenceById(id).getNombre());
        person.setApellido(personRepository.getReferenceById(id).getApellido());
        person.setDomicilio(personRepository.getReferenceById(id).getDomicilio());
        person.setDocumento(personRepository.getReferenceById(id).getDocumento());
        return person;
    }

    @Override
    public Person findPersonById(Long id) {
        personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("No existe una persona con ese ID en la base de datos."));
        return null;
    }

    @Override
    public ResponseEntity<HashMap<String, String>> deletePerson(Long id){
        personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException("No existe una persona con ese ID en la base de datos."));
        personRepository.deleteById(id);
        HashMap<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "La persona con ID: " + id + " fue eliminada correctamente.");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respuesta);
    }
}

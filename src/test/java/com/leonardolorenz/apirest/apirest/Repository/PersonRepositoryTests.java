package com.leonardolorenz.apirest.apirest.Repository;

import com.leonardolorenz.apirest.apirest.Models.Person;
import com.leonardolorenz.apirest.apirest.Repositories.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository personRepository;


    //Paso datos de prueba y si son correctos (no nulos), compruebo si se agrego la persona
    //si el id > 0 & la persona guardada no es nula => la persona existe y fue agregada
    @Test
    public void PersonRepository_SavePerson(){
        Person person = Person.builder()
                .nombre("jorge")
                .apellido("smith")
                .domicilio("ficcion 123")
                .documento("46026112").build();

        Person savedPerson = personRepository.save(person);

        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson.getId()).isGreaterThan(0);
    }

    @Test
    public void PersonRepositoryGetAllPersons(){
        Person person = Person.builder()
                .nombre("jorge")
                .apellido("smith")
                .domicilio("ficcion 123")
                .documento("46026112").build();
        Person person2 = Person.builder()
                .nombre("alberto")
                .apellido("smith")
                .domicilio("ficcion 128")
                .documento("45001312").build();

        personRepository.save(person);
        personRepository.save(person2);

        List<Person> personList = personRepository.findAll();

        Assertions.assertThat(personList).isNotNull();
        Assertions.assertThat(personList.size()).isEqualTo(2);
    }
    @Test
    public void PersonRepositoryFindByIdPerson(){
        Person person = Person.builder()
                .nombre("jorge")
                .apellido("smith")
                .domicilio("ficcion 123")
                .documento("46026112").build();

        personRepository.save(person);

        Person personList = personRepository.findById(person.getId()).get();

        Assertions.assertThat(personList).isNotNull();
    }

    @Test
    public void PersonRepositoryGetByIdPerson(){
        Person person = Person.builder()
                .nombre("jorge")
                .apellido("smith")
                .domicilio("ficcion 123")
                .documento("46026112").build();

        personRepository.save(person);

        Person personSaved = personRepository.getReferenceById(person.getId());
        Assertions.assertThat(personSaved).isNotNull();
    }
    @Test
    public void PersonRepositoryUpdatePerson(){
        Person person = Person.builder()
                .nombre("jorge")
                .apellido("smith")
                .domicilio("ficcion 123")
                .documento("46026112").build();

        personRepository.save(person);

        Person personSave = personRepository.findById(person.getId()).get();
        personSave.setNombre("Jorge");
        personSave.setApellido("Smith");
        personSave.setDomicilio("Ficcion 123 y Accion 124");
        personSave.setDocumento("44001235");

        Person personUpdate = personRepository.save(personSave);

        Assertions.assertThat(personUpdate.getNombre()).isNotNull();
        Assertions.assertThat(personUpdate.getApellido()).isNotNull();
        Assertions.assertThat(personUpdate.getDocumento()).isNotNull();
        Assertions.assertThat(personUpdate.getDomicilio()).isNotNull();
    }
    @Test
    public void PersonRepositoryDeletePerson(){
        Person person = Person.builder()
                .nombre("jorge")
                .apellido("smith")
                .domicilio("ficcion 123")
                .documento("46026112").build();
        personRepository.save(person);

        personRepository.deleteById(person.getId());
        Optional<Person> personReturn = personRepository.findById(person.getId());

        Assertions.assertThat(personReturn).isEmpty();
    }
}

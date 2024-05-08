package com.leonardolorenz.apirest.apirest.Repository;

import com.leonardolorenz.apirest.apirest.Models.Person;
import com.leonardolorenz.apirest.apirest.Repositories.PersonRepository;
import com.leonardolorenz.apirest.apirest.exceptions.PersonAlredyExistException;
import com.leonardolorenz.apirest.apirest.exceptions.PersonNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PersonRepositoryFailedTests {

    @MockBean
    private PersonRepository personRepositoryFailedTests;

    @Test
    public void PersonRepository_FailSavePersonTest(){
        when(personRepositoryFailedTests.save(any(Person.class))).thenThrow(new PersonAlredyExistException());
        org.junit.jupiter.api.Assertions.assertThrows(PersonAlredyExistException.class, () -> {
            Person person = Person.builder()
                    .nombre("jorge")
                    .apellido("smith")
                    .domicilio("ficcion 123")
                    .documento("46026112").build();
            personRepositoryFailedTests.save(person);
        });
    }
    @Test
    public void PersonRepository_FailGetByIdPersonTest(){
        when(personRepositoryFailedTests.findById(anyLong())).thenThrow(new PersonNotFoundException());
        org.junit.jupiter.api.Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personRepositoryFailedTests.findById(1L);
        });
    }
}

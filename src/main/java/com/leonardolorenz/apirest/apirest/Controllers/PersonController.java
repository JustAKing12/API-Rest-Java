package com.leonardolorenz.apirest.apirest.Controllers;

import com.leonardolorenz.apirest.apirest.Models.Person;
import com.leonardolorenz.apirest.apirest.Services.PersonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Persons")
public class PersonController {
    @Autowired
    private PersonServiceImpl personService;
    @Operation(summary = "View all Persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persons correctly obtained",
                    content={
                            @Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))
                    }),
            @ApiResponse(responseCode = "500", description = "params error", content = @Content)
    })
    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }
    @Operation(summary = "View Person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "person correctly obtained",
                    content={
                            @Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))
                    }),
            @ApiResponse(responseCode = "500", description = "params error", content = @Content)
    })
    @GetMapping("/{id}")
    public Person getPersonById(@Parameter(description = "person id",example = "1") @PathVariable Long id) {
        return personService.getPersonById(id);
    }
    @Operation(summary = "Create a new Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "person successfully created",
            content={
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))
            }),
            @ApiResponse(responseCode = "500", description = "server error", content = @Content),
            @ApiResponse(responseCode = "400", description = "invalid data (response)", content = @Content)
    })
    @PostMapping
    public Person createPerson(@RequestBody Person person){
        return personService.savePerson(person);
    }
    @Operation(summary = "Edit person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "person successfully edited", content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))
            }),
            @ApiResponse(responseCode = "500", description = "params error", content = @Content)
    })
    @PutMapping("/{id}")
    public Person updatePerson(@Parameter(description = "person id", example = "1") @PathVariable Long id, @RequestBody Person detallesPerson){
        return personService.updatePerson(id, detallesPerson);
    }
    @Operation(summary = "Delete person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "person successfully deleted",
                    content={
                            @Content(mediaType = "application/json",schema = @Schema(implementation = Person.class))
                    }),
            @ApiResponse(responseCode = "500", description = "params error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> deletePerson(@PathVariable Long id){
        return personService.deletePerson(id);
    }
}

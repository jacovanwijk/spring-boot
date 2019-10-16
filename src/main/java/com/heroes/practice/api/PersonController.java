package com.heroes.practice.api;

import com.heroes.practice.model.Person;
import com.heroes.practice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public int addPerson(@Valid @NotNull @RequestBody Person person) {
        return personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "/{personID}")
    public Person getPersonById(@PathVariable("personID") UUID id) {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "/{personID}")
    public int deletePerson(@PathVariable("personID") UUID id) {
        return personService.deletePerson(id);
    }

    @PutMapping(path = "/{personID}")
    public int updatePerson(@PathVariable("personID") UUID id, @Valid @NotNull @RequestBody Person personToUpdate) {
        return personService.updatePerson(id, personToUpdate);
    }
}

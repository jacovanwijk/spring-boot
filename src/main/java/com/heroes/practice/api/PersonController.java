package com.heroes.practice.api;

import com.heroes.practice.model.Person;
import com.heroes.practice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
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
    public void deletePerson(@PathVariable("personID") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path = "/{personID}")
    public void updatePerson(@PathVariable("personID") UUID id, @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
}

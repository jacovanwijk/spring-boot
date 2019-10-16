package com.heroes.practice.dao;

import com.heroes.practice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository()
public class PostgresPersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;
    private static List<Person> DB = new ArrayList<>();

    @Autowired
    public PostgresPersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO person (id, name) VALUES (?, ?)";
        String name = person.getName();
        return jdbcTemplate.update(sql, new Object[]{id, name});
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id = ?";
        try {
            Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
                UUID personId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                return new Person(id, name);
            });
            return Optional.of(person);
        } catch (EmptyResultDataAccessException e) {
            return Optional.ofNullable(null);
        }
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM person WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql = "UPDATE person SET name = ? WHERE id = ?";
        String name = person.getName();
        return jdbcTemplate.update(sql, new Object[]{name, id});
    }
}

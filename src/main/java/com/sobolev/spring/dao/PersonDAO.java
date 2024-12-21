package com.sobolev.spring.dao;

import com.sobolev.spring.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> persons = session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        return persons;
    }

    @Transactional
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }


    //    public Optional<Person> show(String email) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE email=?", new BeanPropertyRowMapper<>(Person.class), email)
//                .stream().findAny();
//    }
//
    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }
}
//
//    /*Тестирую производительность пакетной вставки*/
//
//    public void testMultipleUpdate() {
//        List<Person> people = create1000People();
//        long before = System.currentTimeMillis();
//
//        for (Person person : people) {
//            jdbcTemplate.update("INSERT INTO person VALUES (?,?,?,?,?)",person.getId(), person.getName(), person.getAge(), person.getEmail(), person.getAddress());
//        }
//
//        long after = System.currentTimeMillis();
//
//        System.out.println("Time: " + (after - before));
//    }
//
//    private List<Person> create1000People() {
//        List<Person> people = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(i,"Name"+i,30,"test"+i+"@mail.com", "Russia, Tula, 666666"));
//        }
//        return people;
//    }
//
//    public void testBatchUpdate() {
//        List<Person> people = create1000People();
//        long before = System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO person VALUES(?,?,?,?,?)",
//                new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setInt(1, people.get(i).getId());
//                ps.setString(2, people.get(i).getName());
//                ps.setInt(3, people.get(i).getAge());
//                ps.setString(4, people.get(i).getEmail());
//                ps.setString(5, people.get(i).getAddress());
//            }
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        });
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time: " + (after - before));
//    }
//}

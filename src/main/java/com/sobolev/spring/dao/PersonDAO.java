package com.sobolev.spring.dao;

import com.sobolev.spring.models.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
import java.util.*;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1(){
        Session session = entityManager.unwrap(Session.class);

//        // 1
//        List<Person> people = session.createQuery("Select p from Person p", Person.class)
//                .getResultList();
//
//        // N
//        for (Person person : people) {
//            System.out.println("Person: " + person.getName() + "has: " + person.getItems());
//        }

        //Solving

        Set<Person> people = new HashSet<>(session.createQuery("select p from Person p LEFT JOIN FETCH p.items")
                .getResultList());

        for (Person person : people) {
            System.out.println("Person: " + person.getName() + "has: " + person.getItems());
        }
    }
}
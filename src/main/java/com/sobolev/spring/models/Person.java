package com.sobolev.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Name shouldn`t be empty")
    @Size(min = 2,max = 30,message = "Name should be between 2 nd 30 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 1, message = "Age should be greater than 0")
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner")
    private List<Item> items;

    public Person(String name, int age) {
//        this.id = id;
        this.name = name;
        this.age = age;
//        this.email = email;
//        this.address = address;
    }

    public Person() {

    }

//    @NotEmpty(message = "Name shouldn`t be empty")
//    @Email(message = "Email should be valid")
//    private String email;

//    // Country, City, index(6 digits)
//    // Russia, Moscow, 123456
//    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in this format: Country, City, Index(6 digits)")
//    private String address;

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

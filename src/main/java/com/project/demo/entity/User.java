package com.project.demo.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private int age;
    private String gender;
    private String role;
    private String active;
    private Date registerDate;


    public User(int id, String name, String password, String email, int age, String gender, String role, String active, Date registerDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.active = active;
        this.registerDate = registerDate;
    }
}
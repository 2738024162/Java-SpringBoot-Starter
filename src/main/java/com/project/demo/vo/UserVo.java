package com.project.demo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserVo {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String role;
    private String email;
    private String active;
    private Date registerDate;
}

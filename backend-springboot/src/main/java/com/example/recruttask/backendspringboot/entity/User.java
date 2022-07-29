package com.example.recruttask.backendspringboot.entity;

import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_Id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "login")
    private String login;

    public Integer getUserId() {
        return userId;
    }


    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


    public String getLogin() {
        return login;
    }

}

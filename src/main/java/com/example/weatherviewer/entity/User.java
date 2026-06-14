package com.example.weatherviewer.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users", indexes = @Index(name = "idx_login", columnList = "login"))
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false,unique = true)
    private String login;

    @Column(name = "password", nullable = false,unique = true)
    private String password;


}

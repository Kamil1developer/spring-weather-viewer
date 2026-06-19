package com.example.weatherviewer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", indexes = @Index(name = "idx_login", columnList = "login"))
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false,unique = true)
    @NonNull
    private String login;

    @Column(name = "password", nullable = false,unique = true)
    @NonNull
    private String password;


}

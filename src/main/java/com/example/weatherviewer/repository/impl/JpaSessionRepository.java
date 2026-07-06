package com.example.weatherviewer.repository.impl;

import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.repository.SessionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class JpaSessionRepository  implements SessionRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<User> findByLogin(String userName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public void save(Session session) {

    }
}

package com.example.weatherviewer.repository;

import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    public Optional<User> findByLogin(String userName);

    public List<User> findAll();
    public void save(Session session);
}

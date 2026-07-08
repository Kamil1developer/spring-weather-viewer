package com.example.weatherviewer.repository.impl;

import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository


public class JpaUserRepository implements UserRepository {
    @PersistenceContext
    private  EntityManager entityManager;

    public Optional<User> findByLogin(String login){
        return entityManager.createQuery("select u from User u where u.login = :login", User.class)
                .setParameter("login", login)
                .getResultStream()
                .findFirst();
    }

    public List<User> findAll(){
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }
    public User save(User user){
        entityManager.persist(user);
        return user;
    }
}

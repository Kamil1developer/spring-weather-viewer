package com.example.weatherviewer.repository;

import com.example.weatherviewer.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository


public class UserRepository {
    @PersistenceContext
    private  EntityManager entityManager;

    public Optional<User> findByLogin(String userName){
        return entityManager.createQuery("select u from User u where u.login = :userName", User.class)
                .setParameter("userName", userName)
                .getResultStream()
                .findFirst();
    }

    public List<User> findAll(){
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }
}

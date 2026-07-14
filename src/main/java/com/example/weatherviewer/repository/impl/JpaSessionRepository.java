package com.example.weatherviewer.repository.impl;

import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.repository.SessionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaSessionRepository  implements SessionRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Session> findByUserId(Long userId) {
        return entityManager.createQuery("select s from Session s where s.userId = :userId", Session.class)
                .setParameter("userId", userId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Session save(Session session) {
        entityManager.persist(session);
        return session;
    }

    @Override
    public void deleteBySessionId(UUID id) {
        entityManager.createQuery("delete from Session s where s.id = :id ")
                .setParameter("id", id)
                .executeUpdate();
    }
}

package com.example.weatherviewer.repository.impl;

import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.repository.SessionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaSessionRepository  implements SessionRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Session> findByUserId(User user) {
        return entityManager.createQuery("select s from Session s where s.user = :user", Session.class)
                .setParameter("user", user)
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

    @Override
    public boolean existsByIdAndExpiresAtBefore(UUID sessionId, Instant now) {
        return entityManager.createQuery("""
                select s from Session s where s.id = :sessionId and
                :now < s.expiresAt
                """)
                .setParameter("sessionId",sessionId)
                .setParameter("now", now)
                .getResultStream()
                .findFirst()
                .isPresent();
    }

    @Override
    public Optional<User> findBySessionId(UUID sessionId) {
        return entityManager.createQuery("select s.user from Session s where s.id = :sessionId",User.class)
                .setParameter("sessionId",sessionId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public void deleteByExpiresAtBefore(Instant now) {
        entityManager.createQuery("delete from Session s where :now > s.expiresAt")
                .setParameter("now",now)
                .executeUpdate();
    }

}

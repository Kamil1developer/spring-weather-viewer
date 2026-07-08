package com.example.weatherviewer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @Column(name = "id", nullable = false)
    @NonNull
    private UUID id;

    @Column(name = "user_id", nullable = false)
    @NonNull
    private Long userId;

    @Column(name = "expires_at", nullable = false)
    @NonNull
    private Instant expiresAt;
}

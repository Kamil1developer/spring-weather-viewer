package com.example.weatherviewer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Session {
    @Id
    @Column(name = "id", nullable = false)
    @NonNull
    private UUID id;

    @Column(name = "userId", nullable = false)
    @NonNull
    private Long userId;

    @Column(name = "expiresAt", nullable = false)
    @NonNull
    private Instant expiresAt;
}

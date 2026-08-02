package com.example.weatherviewer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
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

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    @NonNull
    private User user;

    @Column(name = "expires_at", nullable = false)
    @NonNull
    private Instant expiresAt;
}

package com.example.weatherviewer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "locations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RequiredArgsConstructor
public class Location {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    @NonNull
    private User user;

    @Column(name = "latitude", nullable = false)
    @NonNull
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    @NonNull
    private BigDecimal longitude;

    @Column(name = "state")
    @NonNull
    private String state;

    @Column(name = "country")
    @NonNull
    private String country;
}

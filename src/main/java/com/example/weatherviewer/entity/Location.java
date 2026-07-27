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
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Long userId;

    @Column(name = "latitude", nullable = false)
    @NonNull
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    @NonNull
    private BigDecimal longitude;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

}

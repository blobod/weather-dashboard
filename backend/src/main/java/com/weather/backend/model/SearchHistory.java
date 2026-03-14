package com.weather.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "search_history")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private Double temperature;
    private String weatherDescription;
    private String country;
    private LocalDateTime searchedAt;

    @PrePersist
    public void prePersist() {
        searchedAt = LocalDateTime.now();
    }
}
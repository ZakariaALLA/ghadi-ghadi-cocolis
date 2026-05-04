package com.ghadi.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    private String departureCity;
    private String arrivalCity;
    private LocalDate tripDate;
    
    // Status: PLANNED, IN_PROGRESS, COMPLETED, CANCELLED
    private String status = "PLANNED";
    
    private LocalDateTime createdAt = LocalDateTime.now();
}

package com.ghadi.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "parcel_ads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcelAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    private String title;
    private String description;
    
    // Size categories: S, M, L, XL
    private String sizeCategory;
    
    private String departureCity;
    private String arrivalCity;
    
    private Double proposedPrice;
    
    // Status: OPEN, ASSIGNED, DELIVERED, CANCELLED
    private String status = "OPEN";
    
    private LocalDateTime createdAt = LocalDateTime.now();
}

package com.ghadi.backend.repository;

import com.ghadi.backend.model.ParcelAd;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParcelAdRepository extends JpaRepository<ParcelAd, Long> {
    List<ParcelAd> findByStatus(String status);
    List<ParcelAd> findByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);
}

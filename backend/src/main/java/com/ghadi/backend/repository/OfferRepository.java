package com.ghadi.backend.repository;

import com.ghadi.backend.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByParcelAdId(Long parcelAdId);
    List<Offer> findByDriverId(Long driverId);
}

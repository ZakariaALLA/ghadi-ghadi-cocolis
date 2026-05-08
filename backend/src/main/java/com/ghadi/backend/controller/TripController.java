package com.ghadi.backend.controller;

import com.ghadi.backend.model.Trip;
import com.ghadi.backend.model.User;
import com.ghadi.backend.repository.TripRepository;
import com.ghadi.backend.repository.UserRepository;
import com.ghadi.backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TripController {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<Trip> publishTrip(@RequestBody Trip trip, @RequestHeader("Authorization") String token) {
        String email = jwtUtils.extractEmail(token.substring(7));
        User driver = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        trip.setDriver(driver);
        return ResponseEntity.ok(tripRepository.save(trip));
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripRepository.findAll());
    }
}

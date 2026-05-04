package com.ghadi.backend.controller;

import com.ghadi.backend.model.ParcelAd;
import com.ghadi.backend.repository.ParcelAdRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parcels")
@CrossOrigin(origins = "*")
public class ParcelAdController {

    private final ParcelAdRepository repository;

    public ParcelAdController(ParcelAdRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ParcelAd> getAllParcels() {
        return repository.findAll();
    }

    @PostMapping
    public ParcelAd createParcel(@RequestBody ParcelAd parcel) {
        // In a real app, the sender would be taken from the JWT Context
        return repository.save(parcel);
    }
}

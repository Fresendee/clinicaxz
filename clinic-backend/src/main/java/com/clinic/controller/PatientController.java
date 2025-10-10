package com.clinic.controller;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Patient> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return repo.save(patient);
    }
}

package com.clinic.controller;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:3000") // permite que o frontend acesse
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
}

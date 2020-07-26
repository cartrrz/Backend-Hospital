package com.hospital.backendhospital.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String Address;
    private Long HospitalId;
}

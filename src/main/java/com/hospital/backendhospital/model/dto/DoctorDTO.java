package com.hospital.backendhospital.model.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class DoctorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String Address;
}

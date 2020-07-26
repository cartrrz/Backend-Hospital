package com.hospital.backendhospital.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitNoteDTO {
    private Long id;
    private LocalDate visitDate;
    private String description;
    private Long patientId;
    private Long doctorId;
}

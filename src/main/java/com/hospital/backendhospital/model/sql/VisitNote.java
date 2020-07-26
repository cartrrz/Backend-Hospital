package com.hospital.backendhospital.model.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@ToString(callSuper = true, exclude = { "patient, doctor" })
@EqualsAndHashCode(callSuper = true, exclude = { "patient, doctor" })
@Table(name = "visit_note")
public class VisitNote extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate dateVisit;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}

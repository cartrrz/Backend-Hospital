package com.hospital.backendhospital.repository;

import com.hospital.backendhospital.model.sql.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}

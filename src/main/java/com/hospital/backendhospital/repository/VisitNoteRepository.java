package com.hospital.backendhospital.repository;

import com.hospital.backendhospital.model.sql.VisitNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitNoteRepository extends JpaRepository<VisitNote, Long> {

}

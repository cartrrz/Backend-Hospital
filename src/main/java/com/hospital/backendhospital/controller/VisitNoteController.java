package com.hospital.backendhospital.controller;

import com.hospital.backendhospital.model.ObjectResponse;
import com.hospital.backendhospital.model.dto.VisitNoteDTO;
import com.hospital.backendhospital.model.sql.Doctor;
import com.hospital.backendhospital.model.sql.Patient;
import com.hospital.backendhospital.model.sql.VisitNote;
import com.hospital.backendhospital.repository.DoctorRepository;
import com.hospital.backendhospital.repository.PatientRepository;
import com.hospital.backendhospital.repository.VisitNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/visit")
public class VisitNoteController {
    
    @Autowired
    VisitNoteRepository visitNoteRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping
    @ResponseBody
    public ObjectResponse<List<VisitNoteDTO>> getAll(){
        ObjectResponse response = new ObjectResponse();
        try{
            List<VisitNoteDTO> visitNotes = visitNoteRepository.findAll().stream().map(visitNote -> {
                VisitNoteDTO dto = new VisitNoteDTO();
                dto.setId(visitNote.getId());
                dto.setVisitDate(visitNote.getDateVisit());
                dto.setDescription(visitNote.getDescription());
                return dto;
            }).collect(Collectors.toList());

            response.setSuccess(true);
            response.setObject(visitNotes);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ObjectResponse<VisitNoteDTO>fendById(@PathVariable("id")Long id){
        ObjectResponse response= new ObjectResponse();
        try{
            VisitNote visitNote = visitNoteRepository.findById(id).orElse(null);
            VisitNoteDTO dto = new VisitNoteDTO();
            dto.setId(visitNote.getId());
            dto.setVisitDate(visitNote.getDateVisit());
            dto.setDescription(visitNote.getDescription());
            response.setObject(dto);
            response.setSuccess(true);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/create")
    @ResponseBody
    public ObjectResponse<VisitNote>create(@RequestBody VisitNoteDTO visitNoteDto){
        ObjectResponse response = new ObjectResponse();
        try{
            VisitNote visitNote = new VisitNote();
            visitNote.setDescription(visitNoteDto.getDescription());
            visitNote.setDateVisit(visitNoteDto.getVisitDate());
            visitNote.setCreatedDate(LocalDateTime.now());
            visitNote.setModifiedDate(LocalDateTime.now());

            Patient patient = patientRepository.findById(visitNoteDto.getPatientId()).orElse(null);
            visitNote.setPatient(patient);

            Doctor doctor = doctorRepository.findById(visitNoteDto.getDoctorId()).orElse(null);
            visitNote.setDoctor(doctor);

            visitNoteRepository.save(visitNote);
            response.setSuccess(true);
            response.setObject(visitNote);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ObjectResponse<VisitNoteDTO> update(@PathVariable("id") Long id, @RequestBody VisitNoteDTO visitNoteDto) {
        ObjectResponse response = new ObjectResponse();
        try{
            VisitNote visitNote = visitNoteRepository.findById(id).orElse(null);
            visitNote.setDateVisit(visitNoteDto.getVisitDate());
            visitNote.setDescription(visitNoteDto.getDescription());
            visitNote.setModifiedDate(LocalDateTime.now());
            visitNoteRepository.save(visitNote);

            response.setSuccess(true);
            response.setObject(visitNoteDto);

        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

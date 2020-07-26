package com.hospital.backendhospital.controller;

import com.hospital.backendhospital.model.ObjectResponse;
import com.hospital.backendhospital.model.dto.PatientDTO;
import com.hospital.backendhospital.model.sql.Hospital;
import com.hospital.backendhospital.model.sql.Patient;
import com.hospital.backendhospital.repository.HospitalRepository;
import com.hospital.backendhospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @GetMapping
    @ResponseBody
    public ObjectResponse<List<PatientDTO>> getAll(){
        ObjectResponse response = new ObjectResponse();
        try{
            List<PatientDTO> patients = patientRepository.findAll().stream().map(patient -> {
                PatientDTO dto = new PatientDTO();
                dto.setId(patient.getId());
                dto.setFirstName(patient.getFirstName());
                dto.setLastName(patient.getLastName());
                dto.setAddress(patient.getAddress());
                dto.setBirthDate(patient.getBirthDate());
                return dto;
            }).collect(Collectors.toList());

            response.setSuccess(true);
            response.setObject(patients);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ObjectResponse<PatientDTO>fendById(@PathVariable("id")Long id){
        ObjectResponse response= new ObjectResponse();
        try{
            Patient patient = patientRepository.findById(id).orElse(null);
            PatientDTO dto = new PatientDTO();
            dto.setId(patient.getId());
            dto.setFirstName(patient.getFirstName());
            dto.setLastName(patient.getLastName());
            dto.setAddress(patient.getAddress());
            dto.setBirthDate(patient.getBirthDate());
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
    public ObjectResponse<Patient>create(@RequestBody PatientDTO patientDto){
        ObjectResponse response = new ObjectResponse();
        try{
            Patient patient= new Patient();
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setAddress(patientDto.getAddress());
            patient.setBirthDate(patientDto.getBirthDate());
            patient.setCreatedDate(LocalDateTime.now());
            patient.setModifiedDate(LocalDateTime.now());

            Hospital hospital = hospitalRepository.findById(patientDto.getHospitalId()).orElse(null);
            patient.setHospital(hospital);
            patientRepository.save(patient);
            response.setSuccess(true);
            response.setObject(patient);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ObjectResponse<PatientDTO> update(@PathVariable("id") Long id, @RequestBody PatientDTO patientDto) {
        ObjectResponse response = new ObjectResponse();
        try{
            Patient patient = patientRepository.findById(id).orElse(null);
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setAddress(patientDto.getAddress());
            patient.setBirthDate(patientDto.getBirthDate());
            patient.setModifiedDate(LocalDateTime.now());
            patientRepository.save(patient);

            response.setSuccess(true);
            response.setObject(patientDto);

        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

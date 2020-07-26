package com.hospital.backendhospital.controller;

import com.hospital.backendhospital.model.ObjectResponse;
import com.hospital.backendhospital.model.dto.DoctorDTO;
import com.hospital.backendhospital.model.sql.Doctor;
import com.hospital.backendhospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping
    @ResponseBody
    public ObjectResponse<List<DoctorDTO>> getAll(){
        ObjectResponse response = new ObjectResponse();
        try{
            List<DoctorDTO> doctors = doctorRepository.findAll().stream().map(doctor -> {
                DoctorDTO dto = new DoctorDTO();
                dto.setId(doctor.getId());
                dto.setFirstName(doctor.getFirstName());
                dto.setLastName(doctor.getLastName());
                dto.setAddress(doctor.getAddress());
                dto.setBirthDate(doctor.getBirthDate());
                return dto;
            }).collect(Collectors.toList());

            response.setSuccess(true);
            response.setObject(doctors);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ObjectResponse<DoctorDTO>fendById(@PathVariable("id")Long id){
        ObjectResponse response= new ObjectResponse();
        try{
            Doctor doctor = doctorRepository.findById(id).orElse(null);
            DoctorDTO dto = new DoctorDTO();
            dto.setId(doctor.getId());
            dto.setFirstName(doctor.getFirstName());
            dto.setLastName(doctor.getLastName());
            dto.setAddress(doctor.getAddress());
            dto.setBirthDate(doctor.getBirthDate());
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
    public ObjectResponse<Doctor>create(@RequestBody Doctor doctor){
        ObjectResponse response = new ObjectResponse();
        try{
            doctor.setCreatedDate(LocalDateTime.now());
            doctor.setModifiedDate(LocalDateTime.now());
            doctorRepository.save(doctor);
            response.setSuccess(true);
            response.setObject(doctor);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ObjectResponse<DoctorDTO> update(@PathVariable("id") Long id, @RequestBody DoctorDTO doctorDto) {
        ObjectResponse response = new ObjectResponse();
        try{
            Doctor doctor = doctorRepository.findById(id).orElse(null);
            doctor.setFirstName(doctorDto.getFirstName());
            doctor.setLastName(doctorDto.getLastName());
            doctor.setAddress(doctorDto.getAddress());
            doctor.setBirthDate(doctorDto.getBirthDate());
            doctor.setModifiedDate(LocalDateTime.now());
            doctorRepository.save(doctor);

            response.setSuccess(true);
            response.setObject(doctorDto);

        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

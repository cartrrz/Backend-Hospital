package com.hospital.backendhospital.controller;

import com.hospital.backendhospital.model.ObjectResponse;
import com.hospital.backendhospital.model.dto.HospitalDTO;
import com.hospital.backendhospital.model.sql.Hospital;
import com.hospital.backendhospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/hospital")
public class HospitalController {

    @Autowired
    HospitalRepository hospitalRepository;

    @GetMapping
    @ResponseBody
    public ObjectResponse<List<HospitalDTO>> getAll(){
        ObjectResponse response = new ObjectResponse();
        try{
            List<HospitalDTO> hospitals = hospitalRepository.findAll().stream().map(hospital -> {
                HospitalDTO dto = new HospitalDTO();
                dto.setId(hospital.getId());
                dto.setName(hospital.getName());
                return dto;
            }).collect(Collectors.toList());

            response.setSuccess(true);
            response.setObject(hospitals);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ObjectResponse<HospitalDTO>fendById(@PathVariable("id")Long id){
        ObjectResponse response= new ObjectResponse();
        try{
            Hospital hospital = hospitalRepository.findById(id).orElse(null);
            HospitalDTO dto = new HospitalDTO();
            dto.setId(hospital.getId());
            dto.setName(hospital.getName());
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
    public ObjectResponse<Hospital>create(@RequestBody Hospital hospital){
        ObjectResponse response = new ObjectResponse();
        try{
            hospital.setCreatedDate(LocalDateTime.now());
            hospital.setModifiedDate(LocalDateTime.now());
            hospitalRepository.save(hospital);
            response.setSuccess(true);
            response.setObject(hospital);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ObjectResponse<HospitalDTO> update(@PathVariable("id") Long id, @RequestBody HospitalDTO hospitalDto) {
        ObjectResponse response = new ObjectResponse();
        try{
            Hospital hospital = hospitalRepository.findById(id).orElse(null);
            hospital.setName(hospitalDto.getName());
            hospital.setModifiedDate(LocalDateTime.now());
            hospitalRepository.save(hospital);
            response.setSuccess(true);
            response.setObject(hospitalDto);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

package com.hospital.backendhospital.controller;

import com.hospital.backendhospital.model.ObjectResponse;
import com.hospital.backendhospital.model.dto.SpecialityDTO;
import com.hospital.backendhospital.model.sql.Speciality;
import com.hospital.backendhospital.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/speciality")
public class SpecialityController {

    @Autowired
    SpecialityRepository specialityRepository;

    @GetMapping
    @ResponseBody
    public ObjectResponse<List<SpecialityDTO>> getAll(){
        ObjectResponse response = new ObjectResponse();
        try{
            List<SpecialityDTO> specialitys = specialityRepository.findAll().stream().map(speciality -> {
                SpecialityDTO dto = new SpecialityDTO();
                dto.setId(speciality.getId());
                dto.setName(speciality.getName());
                dto.setDescription(speciality.getDescription());
                return dto;
            }).collect(Collectors.toList());

            response.setSuccess(true);
            response.setObject(specialitys);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ObjectResponse<SpecialityDTO>fendById(@PathVariable("id")Long id){
        ObjectResponse response= new ObjectResponse();
        try{
            Speciality speciality = specialityRepository.findById(id).orElse(null);
            SpecialityDTO dto = new SpecialityDTO();
            dto.setId(speciality.getId());
            dto.setName(speciality.getName());
            dto.setDescription(speciality.getDescription());
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
    public ObjectResponse<Speciality>create(@RequestBody Speciality speciality){
        ObjectResponse response = new ObjectResponse();
        try{
            speciality.setCreatedDate(LocalDateTime.now());
            speciality.setModifiedDate(LocalDateTime.now());
            specialityRepository.save(speciality);
            response.setSuccess(true);
            response.setObject(speciality);
        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ObjectResponse<SpecialityDTO> update(@PathVariable("id") Long id, @RequestBody SpecialityDTO specialityDto) {
        ObjectResponse response = new ObjectResponse();
        try{
            Speciality speciality = specialityRepository.findById(id).orElse(null);
            speciality.setName(specialityDto.getName());
            speciality.setDescription(specialityDto.getDescription());
            speciality.setModifiedDate(LocalDateTime.now());
            specialityRepository.save(speciality);

            response.setSuccess(true);
            response.setObject(specialityDto);

        }catch(Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }        
}

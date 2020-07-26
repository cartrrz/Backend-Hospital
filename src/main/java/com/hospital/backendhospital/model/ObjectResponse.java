package com.hospital.backendhospital.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectResponse<T> {
    private boolean success = true;
    private String message;
    private T object;
}

package com.sms.uk.skripsi.module.ScholarshipType.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ScholarshipTypeRequest {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("scholarship_name")
    private String scholarshipName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("isActive")
    private Boolean isActive;

}

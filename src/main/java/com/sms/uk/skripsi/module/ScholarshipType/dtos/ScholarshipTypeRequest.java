package com.sms.uk.skripsi.module.ScholarshipType.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

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

    @JsonProperty("applicant_quota")
    private Integer applicantQuota;

    @JsonProperty("minimum_gpa")
    private BigDecimal minimumGpa;

    @JsonProperty("minimum_semester")
    private Integer minimumSemester;

}

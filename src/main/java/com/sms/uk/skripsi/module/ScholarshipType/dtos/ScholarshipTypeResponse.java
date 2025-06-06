package com.sms.uk.skripsi.module.ScholarshipType.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ScholarshipTypeResponse {

    private String uuid;

    private String scholarshipName;

    private boolean isActive;

    private String description;

    private Integer applicantQuota;

    private BigDecimal minimumGpa;

    private Integer minimumSemester;

    private String createdAt;

    private String createdBy;

    private String updatedAt;

    private String updatedBy;
}

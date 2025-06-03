package com.sms.uk.skripsi.module.ScholarshipType.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScholarshipTypeResponse {

    private String uuid;

    private String scholarshipName;

    private String description;

    private String createdAt;

    private String createdBy;

    private String updatedAt;

    private String updatedBy;
}

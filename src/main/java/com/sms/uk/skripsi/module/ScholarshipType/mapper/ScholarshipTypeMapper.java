package com.sms.uk.skripsi.module.ScholarshipType.mapper;

import com.sms.uk.skripsi.core.util.DateTimeUtil;
import com.sms.uk.skripsi.module.ScholarshipType.dtos.ScholarshipTypeRequest;
import com.sms.uk.skripsi.module.ScholarshipType.dtos.ScholarshipTypeResponse;
import com.sms.uk.skripsi.module.ScholarshipType.entities.ScholarshipType;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipTypeMapper {

    public ScholarshipType convertRequestToEntity(ScholarshipTypeRequest request){

        return ScholarshipType.builder()
                .scholarshipName(request.getScholarshipName())
                .description(request.getDescription())
                .applicantQuota(request.getApplicantQuota())
                .minimumGpa(request.getMinimumGpa())
                .minimumSemester(request.getMinimumSemester())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();
    }

    public ScholarshipType convertRequestToUpdateEntity(ScholarshipTypeRequest request, ScholarshipType announcement){

        return ScholarshipType.builder()
                .uuid(announcement.getUuid())
                .scholarshipName(request.getScholarshipName())
                .description(request.getDescription())
                .applicantQuota(request.getApplicantQuota())
                .minimumGpa(request.getMinimumGpa())
                .minimumSemester(request.getMinimumSemester())
                .isActive(request.getIsActive() != null ? request.getIsActive() : announcement.isActive())
                .createdAt(announcement.getCreatedAt())
                .createdBy(announcement.getCreatedBy())
                .isDeleted(announcement.isDeleted())
                .deletedAt(announcement.getDeletedAt())
                .deletedBy(announcement.getDeletedBy())
                .build();
    }

    public ScholarshipTypeResponse convertEntityToResponse(ScholarshipType scholarshipType){

        return ScholarshipTypeResponse.builder()
                .uuid(scholarshipType.getUuid())
                .scholarshipName(scholarshipType.getScholarshipName())
                .description(scholarshipType.getDescription())
                .applicantQuota(scholarshipType.getApplicantQuota())
                .minimumGpa(scholarshipType.getMinimumGpa())
                .minimumSemester(scholarshipType.getMinimumSemester())
                .isActive(scholarshipType.isActive())
                .createdAt(DateTimeUtil.convertToDetailDateTime(scholarshipType.getCreatedAt()))
                .createdBy(scholarshipType.getCreatedBy())
                .updatedAt(DateTimeUtil.convertToDetailDateTime(scholarshipType.getUpdatedAt()))
                .updatedBy(scholarshipType.getUpdatedBy())
                .build();
    }

}

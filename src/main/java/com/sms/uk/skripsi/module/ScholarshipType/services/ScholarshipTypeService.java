package com.sms.uk.skripsi.module.ScholarshipType.services;

import com.sms.uk.skripsi.module.ScholarshipType.dtos.ScholarshipTypeRequest;
import com.sms.uk.skripsi.module.ScholarshipType.entities.ScholarshipType;
import org.springframework.data.domain.Page;

public interface ScholarshipTypeService {
    ScholarshipType create(ScholarshipTypeRequest request);

    ScholarshipType updateStatus(ScholarshipTypeRequest request);

    Page<ScholarshipType> paging(Integer page, Integer size, String sortBy, String orderBy, String searchByScholarshipName);

    ScholarshipType update(ScholarshipTypeRequest request);

    boolean delete(String uuid);

    ScholarshipType detail(String uuid);
}

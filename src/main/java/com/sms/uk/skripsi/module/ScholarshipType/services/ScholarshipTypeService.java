package com.sms.uk.skripsi.module.ScholarshipType.services;

import com.sms.uk.skripsi.module.ScholarshipType.dtos.ScholarshipTypeRequest;
import com.sms.uk.skripsi.module.ScholarshipType.entities.ScholarshipType;

public interface ScholarshipTypeService {
    ScholarshipType create(ScholarshipTypeRequest request);

    ScholarshipType update(ScholarshipTypeRequest request);

    boolean delete(String uuid);

    ScholarshipType detail(String uuid);
}

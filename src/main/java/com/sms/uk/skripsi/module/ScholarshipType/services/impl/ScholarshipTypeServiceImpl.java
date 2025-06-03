package com.sms.uk.skripsi.module.ScholarshipType.services.impl;

import com.sms.uk.skripsi.base.exception.BaseException;
import com.sms.uk.skripsi.config.response_messages.localization_messages.EnumMessagesKey;
import com.sms.uk.skripsi.module.ScholarshipType.dtos.ScholarshipTypeRequest;
import com.sms.uk.skripsi.module.ScholarshipType.entities.ScholarshipType;
import com.sms.uk.skripsi.module.ScholarshipType.mapper.ScholarshipTypeMapper;
import com.sms.uk.skripsi.module.ScholarshipType.repositories.ScholarshipTypeRepository;
import com.sms.uk.skripsi.module.ScholarshipType.services.ScholarshipTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScholarshipTypeServiceImpl implements ScholarshipTypeService {

    private final ScholarshipTypeRepository repository;
    private final ScholarshipTypeMapper mapper;

    @Override
    public ScholarshipType create(ScholarshipTypeRequest request) {

        log.info("scholarship type create request: {}", request);

        this.isExistByScholarshipName(request);
        ScholarshipType scholarshipType = mapper.convertRequestToEntity(request);

        return repository.save(scholarshipType);
    }

    @Override
    public ScholarshipType update(ScholarshipTypeRequest request) {

        log.info("scholarship type update request: {}", request);
        this.isExistByScholarshipName(request);

        ScholarshipType scholarshipType = this.getDetail(request.getUuid());

        ScholarshipType scholarshipTypeAboutToEdit = mapper.convertRequestToUpdateEntity(request, scholarshipType);

        return repository.save(scholarshipTypeAboutToEdit);
    }

    @Override
    public boolean delete(String uuid) {

        ScholarshipType scholarshipType = this.getDetail(uuid);

        try {

            scholarshipType.setDeleted(true);
            scholarshipType.setDeletedAt(LocalDateTime.now());
            repository.save(scholarshipType);

            return true;
        }catch (Exception e){
            throw new BaseException(EnumMessagesKey.ERROR_DEFAULT);
        }
    }

    @Override
    public ScholarshipType detail(String uuid) {
        return this.getDetail(uuid);
    }

    private ScholarshipType getDetail(String uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new BaseException(EnumMessagesKey.ERROR_NOT_FOUND));
    }


    private void isExistByScholarshipName(ScholarshipTypeRequest request) {
        boolean isExist = request.getUuid() == null? repository.existsByScholarshipName(request.getScholarshipName())
                : repository.existsByScholarshipNameAndUuidNot(request.getDescription(), request.getUuid());

        if (isExist){
            throw new BaseException(EnumMessagesKey.ERROR_DUPLICATED_SCHOLARSHIP_TYPE_NAME);
        }
    }
}

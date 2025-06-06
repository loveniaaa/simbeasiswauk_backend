package com.sms.uk.skripsi.module.ScholarshipType.services.impl;

import com.sms.uk.skripsi.base.exception.BaseException;
import com.sms.uk.skripsi.config.response_messages.localization_messages.EnumMessagesKey;
import com.sms.uk.skripsi.core.util.PagingUtil;
import com.sms.uk.skripsi.module.ScholarshipType.dtos.ScholarshipTypeRequest;
import com.sms.uk.skripsi.module.ScholarshipType.entities.ScholarshipType;
import com.sms.uk.skripsi.module.ScholarshipType.mapper.ScholarshipTypeMapper;
import com.sms.uk.skripsi.module.ScholarshipType.repositories.ScholarshipTypeRepository;
import com.sms.uk.skripsi.module.ScholarshipType.services.ScholarshipTypeService;
import com.sms.uk.skripsi.module.ScholarshipType.spec.ScholarshipTypeSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScholarshipTypeServiceImpl implements ScholarshipTypeService {

    private final ScholarshipTypeRepository repository;
    private final ScholarshipTypeMapper mapper;
    private final ScholarshipTypeSpecification specification;

    @Override
    public ScholarshipType create(ScholarshipTypeRequest request) {

        log.info("scholarship type create request: {}", request);

        this.isExistByScholarshipName(request);
        ScholarshipType scholarshipType = mapper.convertRequestToEntity(request);

        return repository.save(scholarshipType);
    }

    @Override
    public ScholarshipType updateStatus(ScholarshipTypeRequest request) {
        log.info("Updating scholarship type status: {}", request);

        ScholarshipType scholarshipType = this.getDetail(request.getUuid());

        scholarshipType.setActive(request.getIsActive());

        return repository.save(scholarshipType);
    }

    @Override
    public Page<ScholarshipType> paging(Integer page, Integer size, String sortBy, String orderBy, String searchByScholarshipName) {

        try {

            // build page request
            PageRequest pageRequest = PagingUtil.buildPageRequest(page, size);

            List<Pair<String, String>> sorts = new ArrayList<>();

            // set default sort & order direction
            if (StringUtil.isBlank(sortBy) && StringUtil.isBlank(orderBy)){

                sorts.add(Pair.of("scholarshipName", "ascend"));
            }else {
                sorts.add(Pair.of(sortBy, orderBy));
            }

            Sort sort = PagingUtil.listToSort(sorts);

            // spec for feature search
            Specification<ScholarshipType> spec = specification.search(searchByScholarshipName);

            return repository.findAll(spec, pageRequest.withSort(sort));
        }catch (Exception e){
            log.error("Error getting paging data with params, page: {}, size: {}, sortBy: {}, orderBy: {}",
                    page, size, sortBy, orderBy);
            throw new BaseException(EnumMessagesKey.ERROR_DEFAULT);
        }
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

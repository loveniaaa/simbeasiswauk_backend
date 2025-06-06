package com.sms.uk.skripsi.module.ScholarshipType.spec;

import com.sms.uk.skripsi.module.ScholarshipType.entities.ScholarshipType;
import jakarta.persistence.criteria.Predicate;
import org.apache.poi.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScholarshipTypeSpecification {

    public Specification<ScholarshipType> search(String searchByScholarshipName){

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtil.isBlank(searchByScholarshipName)){

                Predicate name = criteriaBuilder.like(criteriaBuilder.lower(root.get("scholarship name")), "%" + searchByScholarshipName.toLowerCase() + "%");

                predicates.add(criteriaBuilder.or(name));
            }

            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isDeleted"), false)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

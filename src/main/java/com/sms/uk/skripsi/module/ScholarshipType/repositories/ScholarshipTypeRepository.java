package com.sms.uk.skripsi.module.ScholarshipType.repositories;

import com.sms.uk.skripsi.module.ScholarshipType.entities.ScholarshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipTypeRepository extends JpaRepository<ScholarshipType, String> {

    @Query("select (count(s) > 0) from ScholarshipType s where s.scholarshipName = ?1")
    boolean existsByScholarshipName(String scholarshipName);

    @Query("select (count(s) > 0) from ScholarshipType s where s.scholarshipName = ?1 and s.uuid <> ?2")
    boolean existsByScholarshipNameAndUuidNot (String scholarshipName, String uuid);

}
package com.sms.uk.skripsi.module.ScholarshipType.repositories;

import com.sms.uk.skripsi.module.ScholarshipType.entities.ScholarshipType;
import com.sms.uk.skripsi.module.faculty_major.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipTypeRepository extends JpaRepository<ScholarshipType, String>, JpaSpecificationExecutor<ScholarshipType> {

    @Query("select (count(s) > 0) from ScholarshipType s where s.scholarshipName = ?1")
    boolean existsByScholarshipName(String scholarshipName);

    @Query("select (count(s) > 0) from ScholarshipType s where s.scholarshipName = ?1 and s.uuid <> ?2")
    boolean existsByScholarshipNameAndUuidNot (String scholarshipName, String uuid);

}
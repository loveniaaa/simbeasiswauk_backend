package com.sms.uk.skripsi.module.ScholarshipType.entities;

import com.sms.uk.skripsi.base.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "t_scholarhsiptype")
@Where(clause = "is_deleted = false")
public class ScholarshipType extends BaseEntity<String> {

    @Id
    @UuidGenerator
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "Scholarship_name")
    private String scholarshipName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}

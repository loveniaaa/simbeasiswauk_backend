package com.sms.uk.skripsi.module.announcement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AnnouncementResponse {

    private String uuid;

    private String title;

    private String description;

    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate displayDate;

    private String createdAt;

    private String createdBy;

    private String updatedAt;

    private String updatedBy;
}

package com.sms.uk.skripsi.module.ScholarshipType.controller;

import com.sms.uk.skripsi.base.responses.SingleRecordResp;
import com.sms.uk.skripsi.config.response_messages.localization_messages.EnumMessagesKey;
import com.sms.uk.skripsi.module.ScholarshipType.dtos.ScholarshipTypeRequest;
import com.sms.uk.skripsi.module.ScholarshipType.mapper.ScholarshipTypeMapper;
import com.sms.uk.skripsi.module.ScholarshipType.services.impl.ScholarshipTypeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scholarshipType")
@RequiredArgsConstructor
@Tag(name = "ScholarshipType")
public class ScholarshipTypeController {

    private final ScholarshipTypeServiceImpl service;
    private final ScholarshipTypeMapper mapper;

    @PostMapping("/create")
    @Operation(summary = "Create scholarship type")
    public ResponseEntity<Object> create(@Valid @RequestBody ScholarshipTypeRequest request){

        var result = service.create(request);

        return ResponseEntity.ok(SingleRecordResp.responseBuilder()
                .result(mapper.convertEntityToResponse(result))
                .messageKey(EnumMessagesKey.SUCCESS_INSERT)
                .build());
    }

    @PutMapping("/update")
    @Operation(summary = "Update scholarship type")
    public ResponseEntity<Object> update(@Valid @RequestBody ScholarshipTypeRequest request){

        var result = service.update(request);

        return ResponseEntity.ok(SingleRecordResp.responseBuilder()
                .result(mapper.convertEntityToResponse(result))
                .messageKey(EnumMessagesKey.SUCCESS_INSERT)
                .build());
    }

    @GetMapping("/detail")
    @Operation(summary = "Detail scholarship type")
    public ResponseEntity<Object> detail(@RequestParam String uuid){

        var result = service.detail(uuid);

        return ResponseEntity.ok(SingleRecordResp.responseBuilder()
                .result(mapper.convertEntityToResponse(result))
                .messageKey(EnumMessagesKey.SUCCESS_INSERT)
                .build());
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete scholarship type")
    public ResponseEntity<Object> create(@RequestParam String uuid){

        return ResponseEntity.ok(SingleRecordResp.responseBuilder()
                .result(service.delete(uuid))
                .messageKey(EnumMessagesKey.SUCCESS_DELETE)
                .build());
    }
}

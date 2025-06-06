package com.sms.uk.skripsi.module.ScholarshipType.controller;

import com.sms.uk.skripsi.base.responses.PagingSortingResp;
import com.sms.uk.skripsi.base.responses.SingleRecordResp;
import com.sms.uk.skripsi.config.response_messages.localization_messages.EnumMessagesKey;
import com.sms.uk.skripsi.module.ScholarshipType.dtos.ScholarshipTypeRequest;
import com.sms.uk.skripsi.module.ScholarshipType.dtos.ScholarshipTypeResponse;
import com.sms.uk.skripsi.module.ScholarshipType.entities.ScholarshipType;
import com.sms.uk.skripsi.module.ScholarshipType.mapper.ScholarshipTypeMapper;
import java.util.UUID;
import java.util.Map;
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

    @PatchMapping("/{uuid}/status")
    public ResponseEntity<ScholarshipTypeResponse> updateScholarshipTypeStatus(
            @PathVariable UUID uuid,
            @RequestBody Map<String, Boolean> requestBody) {

        Boolean newStatus = requestBody.get("isActive");
        ScholarshipTypeRequest statusRequest = new ScholarshipTypeRequest();
        statusRequest.setUuid(String.valueOf(uuid));
        statusRequest.setIsActive(newStatus);

        ScholarshipType updated = service.updateStatus(statusRequest);
        ScholarshipTypeResponse response = mapper.convertEntityToResponse(updated);
        return ResponseEntity.ok(response);
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

    @GetMapping("/get")
    @Operation(summary = "Paging Scholarship type")
    public ResponseEntity<Object> paging(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size,
                                         @RequestParam(required = false) String sortBy,
                                         @RequestParam(required = false) String orderBy,
                                         @RequestParam(required = false) String searchByScholarshipName){

        var results = service.paging(page, size, sortBy, orderBy, searchByScholarshipName);

        return ResponseEntity.ok(PagingSortingResp.responseBuilder()
                .messageKey(EnumMessagesKey.SUCCESS)
                .currentPage(results.getNumber())
                .totalPage(results.getTotalPages())
                .currentSize(results.getSize())
                .records(results.getContent().stream().map(mapper::convertEntityToResponse).toList())
                .totalSize(results.getTotalElements())
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

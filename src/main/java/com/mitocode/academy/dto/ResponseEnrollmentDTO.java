package com.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseEnrollmentDTO(
        LocalDateTime enrollmentDate,
        Integer studentId,
        List<RequestEnrollmentDetailDTO> details,
        Boolean status
) {
}

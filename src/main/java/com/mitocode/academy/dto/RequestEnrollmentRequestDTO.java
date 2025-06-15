package com.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RequestEnrollmentRequestDTO(
        @NotNull(message = "studentId cannot be null")
        Integer studentId,
        @NotNull(message = "details cannot be null")
        @Size(min = 1, max = 10, message = "enrollment must contain between 1 and 10 details")
        @Valid
        List<RequestEnrollmentDetailDTO> details,
        @NotNull(message = "status cannot be null")
        Boolean status
) {
}

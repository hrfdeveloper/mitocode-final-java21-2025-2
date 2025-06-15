package com.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseCourseDTO(
        @NotNull()
        Integer id,
        @NotNull()
        String courseName,
        @NotNull()
        String abbreviation,
        @NotNull()
        Boolean status
) {
}

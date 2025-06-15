package com.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseStudentDTO(
        @NotNull()
        Integer id,
        @NotNull()
        String firstName,
        @NotNull()
        String lastName,
        @NotNull()
        @Length(min = 8, max = 8)
        String DNI,
        @NotNull()
        Integer age

) {
}

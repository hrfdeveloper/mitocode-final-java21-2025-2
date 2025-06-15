package com.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RequestStudentUpdateDTO(
        @NotNull(message = "id cannot be null")
        @Min(value = 1, message = "id should be greater than 0")
        Integer id,
        @NotNull(message = "firstName cannot be null")
        @NotEmpty(message = "firstName cannot be empty")
        @NotBlank(message = "firstName cannot be blank")
        @Length(min=3,max=100,message = "firstName length should be between 3 and 100")
        String firstName,
        @NotNull(message = "lastName cannot be null")
        @NotEmpty(message = "lastName cannot be empty")
        @NotBlank(message = "lastName cannot be blank")
        @Length(min=3,max=100,message = "lastName length should be between 3 and 100")
        String lastName,
        @NotNull(message = "DNI cannot be null")
        @NotEmpty(message = "DNI cannot be empty")
        @Length(min = 8, max = 8, message = "DNI length should be 8")
        @Pattern(regexp = "[0-9]*", message = "DNI should have only numeric chars")
        String DNI,
        @NotNull(message = "age cannot be null")
        @Min(value = 16, message = "Minimum age is 16")
        @Max(value = 100, message = "Maximum age is 100")
        Integer age
) {
}

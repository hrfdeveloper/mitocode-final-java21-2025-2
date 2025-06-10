package com.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RequestCourseUpdateDTO(
        @NotNull(message = "id cannot be null")
        @Min(value = 1, message = "id should be greater than 0")
        Integer id,
        @NotNull(message = "courseName cannot be null")
        @NotEmpty(message = "courseName cannot be empty")
        @NotBlank(message = "courseName cannot be blank")
        @Length(min=3,max=100,message = "courseName length should be between 3 and 100")
        @Pattern(regexp = "^[A-Z][a-zA-Z_0-9]+", message = "courseName should be alphanumeric")
        String courseName,
        @NotNull(message = "abbreviation cannot be null")
        @NotEmpty(message = "abbreviation cannot be empty")
        @NotBlank(message = "abbreviation cannot be blank")
        @Length(min=3,max=10,message = "abbreviation length should be between 3 and 10")
        @Pattern(regexp = "[A-Z]+", message = "abbreviation should be only uppercase letters")
        String abbreviation,
        @NotNull(message = "status cannot be null")
        Boolean status
) {
}

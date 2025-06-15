package com.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RequestEnrollmentDetailDTO(
        @NotNull(message = "courseName cannot be null")
        @NotEmpty(message = "courseName cannot be empty")
        @NotBlank(message = "courseName cannot be blank")
        @Length(min=3,max=100,message = "courseName length should be between 3 and 100")
        @Pattern(regexp = "^[A-Z]+?[a-zA-Z_0-9\s]*", message = "courseName should be alphanumeric")
        String courseName,

        @NotNull(message = "classroom cannot be null")
        @NotEmpty(message = "classroom cannot be empty")
        @NotBlank(message = "classroom cannot be blank")
        @Length(min=3,max=10,message = "classroom length should be between 3 and 10")
        @Pattern(regexp = "[A-Z_0-9]+?", message = "classroom should be only uppercase letters and numbers")
        String classroom
) {
}

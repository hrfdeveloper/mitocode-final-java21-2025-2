package com.mitocode.academy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "enrollment_details", schema = "academy")
public class EnrollmentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private Integer id;

    @ManyToOne(targetEntity = Course.class, optional = false)
    @JoinColumn(
            name = "course_id",
            nullable = false
    )
    private Course course;

    @Column(nullable = false)
    private String classroom;
}

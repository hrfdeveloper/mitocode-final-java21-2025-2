package com.mitocode.academy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "enrollments", schema = "academy")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime enrollmentDateTime;

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(targetEntity = Course.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false, length = 10)
    private String classroom;

    @Column(nullable = false)
    private boolean status = true;

}

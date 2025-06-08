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
    @EqualsAndHashCode.Include
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime enrollmentDateTime;

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(targetEntity = EnrollmentDetails.class, fetch = FetchType.LAZY)
    @JoinColumn(name="enrollment_id")
    private List<EnrollmentDetails> detailsList;

    @Column(nullable = false)
    private boolean status = true;

}

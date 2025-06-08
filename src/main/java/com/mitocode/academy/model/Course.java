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
@Table(name = "courses", schema = "academy")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private Integer id;

    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 100)
    private String courseName;

    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 10)
    private String abbreviation;

    @Column(nullable = false)
    private boolean status = true;

}

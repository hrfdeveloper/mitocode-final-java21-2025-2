package com.mitocode.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "students", schema = "academy")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private Integer id;

    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 50)
    private String firstName;

    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 50)
    private String lastName;

    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 8, unique = true)
    private String DNI;

    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @Min(15)
    @Max(120)
    private Integer age;

}

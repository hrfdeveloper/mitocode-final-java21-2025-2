package com.mitocode.academy.repository;

import com.mitocode.academy.model.Student;

import java.util.Optional;

public interface IStudentRepo extends IGenericRepo<Student,Integer>{

    public abstract Optional<Student> findByDNI(String DNI);

}

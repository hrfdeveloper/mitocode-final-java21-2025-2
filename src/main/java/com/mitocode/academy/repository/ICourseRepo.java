package com.mitocode.academy.repository;

import com.mitocode.academy.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepo extends IGenericRepo<Course,Integer>{

    public abstract Optional<Course> findByCourseName(String courseName);

    public abstract Optional<Course> findByAbbreviation(String abbreviation);

    public abstract List<Course> findAllByStatus(Boolean status);
}

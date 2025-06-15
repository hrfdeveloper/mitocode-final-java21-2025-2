package com.mitocode.academy.service;

import com.mitocode.academy.model.Course;

public interface ICourseService extends ICRUD<Course,Integer> {
    public abstract Course findByName(String courseName) throws Exception;
}

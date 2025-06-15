package com.mitocode.academy.service;

import com.mitocode.academy.model.Enrollment;

import java.util.List;

public interface IEnrollmentService extends ICRUD<Enrollment,Integer>{
    public abstract List<Enrollment> findAllActive() throws Exception;
}

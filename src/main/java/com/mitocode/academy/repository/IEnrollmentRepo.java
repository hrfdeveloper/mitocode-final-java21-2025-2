package com.mitocode.academy.repository;

import com.mitocode.academy.model.Enrollment;

import java.util.List;

public interface IEnrollmentRepo extends  IGenericRepo<Enrollment,Integer> {
    public abstract List<Enrollment> findAllByStatus(Boolean status);
}

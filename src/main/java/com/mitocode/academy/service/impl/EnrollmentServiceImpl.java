package com.mitocode.academy.service.impl;

import com.mitocode.academy.exception.ModelNotFoundException;
import com.mitocode.academy.model.Enrollment;
import com.mitocode.academy.repository.IEnrollmentRepo;
import com.mitocode.academy.service.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements IEnrollmentService {

    private final IEnrollmentRepo repo;

    @Override
    public Enrollment save(Enrollment enrollment) throws Exception {
        return repo.save(enrollment);
    }

    @Override
    public Enrollment update(Integer id, Enrollment enrollment) throws Exception {
        Enrollment current = this.findById(id);
        return repo.save(enrollment);
    }

    @Override
    public List<Enrollment> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Enrollment findById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(()->new ModelNotFoundException("Enrollment with id " + id + " not found."));
    }

    @Override
    public void delete(Integer id) throws Exception {
        Enrollment current = repo.findById(id).orElseThrow(()->new ModelNotFoundException("Enrollment with id " + id + " not found."));
    }

    @Override
    public List<Enrollment> findAllActive() throws Exception {
        return repo.findAllByStatus(true);
    }
}

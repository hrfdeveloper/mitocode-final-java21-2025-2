package com.mitocode.academy.service.impl;

import com.mitocode.academy.exception.ModelNotFoundException;
import com.mitocode.academy.model.Student;
import com.mitocode.academy.repository.ICourseRepo;
import com.mitocode.academy.repository.IStudentRepo;
import com.mitocode.academy.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final IStudentRepo repo;

    @Override
    public Student save(Student student) throws Exception {
        //Validate if student with same DNI already exists
        Student currentStudent = repo.findByDNI(student.getDNI()).orElse(null);
        if (currentStudent != null) {
            System.out.print("Student exist: ");
            System.out.println(currentStudent.toString());
            throw new IllegalArgumentException("Student with DNI " + student.getDNI() + " already exists.");
        }
        return repo.save(student);
    }

    @Override
    public Student update(Integer id, Student student) throws Exception {
        Student currentStudent = repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Student with id " + id + " not found."));
        return repo.save(student);
    }

    @Override
    public List<Student> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Student findById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Student with id " + id + " not found."));
    }

    @Override
    public void delete(Integer id) throws Exception {
        Student currentStudent = repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Student with id " + id + " not found."));
        repo.deleteById(id);
    }

}

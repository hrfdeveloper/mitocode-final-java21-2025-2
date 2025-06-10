package com.mitocode.academy.service.impl;

import com.mitocode.academy.exception.ModelNotFoundException;
import com.mitocode.academy.model.Course;
import com.mitocode.academy.repository.ICourseRepo;
import com.mitocode.academy.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final ICourseRepo repo;

    @Override
    public Course save(Course course) throws Exception {
        //Validate if course exists
        Course currentCourse = repo.findByCourseName(course.getCourseName())
                        .orElse(repo.findByAbbreviation(course.getAbbreviation())
                        .orElse(null));
        if (currentCourse != null) {
            throw new RuntimeException("Course already exists.");
        }
        return repo.save(course);
    }

    @Override
    public Course update(Integer id, Course course) throws Exception {
        //Validate existence of course with id
        Course currentCourse = repo.findById(id).orElseThrow(()->new ModelNotFoundException("Course with id " + id + " not found."));
        if (Objects.equals(currentCourse.getId(), course.getId())) {
            return repo.save(course);
        } else {
            throw new RuntimeException("Course not corresponds with id " + id + ".");
        }
    }

    @Override
    public List<Course> findAll() throws Exception {
        return repo.findAll();
    }

    public List<Course> findAllActive() throws Exception {
        return repo.findAllByStatus(true);
    }

    public List<Course> findAllInactive() throws Exception {
        return repo.findAllByStatus(false);
    }


    @Override
    public Course findById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(()->new ModelNotFoundException("Course with id " + id + " not found."));
    }

    @Override
    public void delete(Integer id) throws Exception {
        Course currentCourse = repo.findById(id).orElseThrow(()->new ModelNotFoundException("Course with id " + id + " not found."));
        repo.deleteById(id);
    }




}

package com.mitocode.academy.controller;

import com.mitocode.academy.dto.*;
import com.mitocode.academy.model.Course;
import com.mitocode.academy.model.Student;
import com.mitocode.academy.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<GenericResponse<ResponseCourseDTO>> findAll(@RequestParam(name = "sortedBy",required = false) String sortedBy) throws Exception {
        List<ResponseCourseDTO> list = new ArrayList<>();
        Comparator<Course> compareByName = (c1, c2) -> {return c1.getCourseName().compareToIgnoreCase(c2.getCourseName());};
        Comparator<Course> compareByAbbreviation = (c1, c2) -> {return c1.getAbbreviation().compareToIgnoreCase(c2.getAbbreviation());};
        Comparator<Course> compareById = Comparator.comparingInt(Course::getId);
                
        if ((sortedBy != null) && (sortedBy.equals("name"))) {
            list = courseService.findAll()
                    .stream()
                    .sorted(compareByName)
                    .map(this::convertToDto)
                    .toList();
        } else if ((sortedBy != null) && (sortedBy.equals("abbreviation"))) {
            list = courseService.findAll()
                    .stream()
                    .sorted(compareByAbbreviation)
                    .map(this::convertToDto)
                    .toList();
        } else {
            list = courseService.findAll()
                    .stream()
                    .sorted(compareById)
                    .map(this::convertToDto)
                    .toList();
        }
        
        return ResponseEntity.ok(new GenericResponse<>(200, "success", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ResponseCourseDTO>> findById(@PathVariable("id") Integer id) throws Exception {
        Course course = courseService.findById(id);
        return ResponseEntity.ok(new GenericResponse<>(200, "success", List.of(convertToDto(course))));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<ResponseCourseDTO>> create(@Valid @RequestBody RequestCourseCreateDTO courseDTO) throws Exception {
        Course course = courseService.save(convertToModel(courseDTO));
        return ResponseEntity.ok(new GenericResponse<>(201, "created", List.of(convertToDto(course))));
    }

    @PutMapping
    public ResponseEntity<GenericResponse<ResponseCourseDTO>> update(@Valid @RequestBody RequestCourseUpdateDTO courseDTO) throws Exception {
        Course course = courseService.update(courseDTO.id(), convertToModel(courseDTO));
        return ResponseEntity.ok(new GenericResponse<>(200, "updated", List.of(convertToDto(course))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> delete(@PathVariable("id") Integer id) throws Exception {
        courseService.delete(id);
        return ResponseEntity.ok(new GenericResponse<>(200, "course-deleted", List.of()));

    }

    private Course convertToModel(RequestCourseCreateDTO courseDTO) {
        return new Course(null,
                            courseDTO.courseName(),
                            courseDTO.abbreviation(),
                            courseDTO.status() );
    }

    private Course convertToModel(RequestCourseUpdateDTO courseDTO) {
        return new Course(courseDTO.id(),
                courseDTO.courseName(),
                courseDTO.abbreviation(),
                courseDTO.status() );
    }

    private ResponseCourseDTO convertToDto(Course course) {
        return new ResponseCourseDTO(course.getId(),
                                     course.getCourseName(),
                                     course.getAbbreviation(),
                                     course.isStatus()
                                     );
    }
}

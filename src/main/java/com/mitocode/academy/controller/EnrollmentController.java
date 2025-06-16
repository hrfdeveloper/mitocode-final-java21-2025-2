package com.mitocode.academy.controller;

import com.mitocode.academy.dto.*;
import com.mitocode.academy.model.Course;
import com.mitocode.academy.model.Enrollment;
import com.mitocode.academy.model.Student;
import com.mitocode.academy.service.ICourseService;
import com.mitocode.academy.service.IEnrollmentService;
import com.mitocode.academy.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final ICourseService courseService;
    private final IStudentService studentService;
    private final IEnrollmentService service;

    @PostMapping
    ResponseEntity<GenericResponse<ResponseEnrollmentDTO>> create(@Valid @RequestBody RequestEnrollmentRequestDTO enrollmentDTO) throws Exception {

        List<Enrollment> enrollments = convertToModel(enrollmentDTO);
        List<Enrollment> enrollmentsCreated = new ArrayList<>();
        for (Enrollment e : enrollments) {
            enrollmentsCreated.add(service.save(e));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new GenericResponse<>(201, "created", List.of(convertToDto(enrollmentsCreated))));
    }

    @GetMapping
    ResponseEntity<String> reportEnrollments() throws Exception {
        List<Enrollment> enrollments = service.findAllActive();
        Map<String,List<String>> report = new TreeMap<>();
        enrollments.sort( (e1,e2) -> {
            if (Objects.equals(e1.getCourse().getCourseName(),e2.getCourse().getCourseName())) {
                return e1.getStudent().getLastName().compareToIgnoreCase(e2.getStudent().getLastName());
            } else {
                return e1.getCourse().getCourseName().compareToIgnoreCase(e2.getCourse().getCourseName());
            }
        });
        StringBuilder sb = new StringBuilder();

        Set<String> courses = enrollments.stream()
                .map((e) -> e.getCourse().getCourseName())
                .distinct()
                .collect(Collectors.toSet());

        courses.forEach((c) -> {
            List<String> students = enrollments.stream()
                    .filter((e)-> e.getCourse().getCourseName().equals(c))
                    .map((e) -> e.getStudent().getFirstName() + " " + e.getStudent().getLastName())
                    .toList();
            report.put(c,students);
        });

        report.forEach((course, students)->{
            sb.append(course).append(System.lineSeparator());
            students.forEach((s) -> sb.append("\t").append(s).append(System.lineSeparator()));
        });
        //System.out.println(sb.toString());

        return ResponseEntity.ok().body(sb.toString());
    }

    private List<Enrollment> convertToModel(RequestEnrollmentRequestDTO enrollmentDTO) throws Exception {
        List<Enrollment> enrollmentList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();

        for (RequestEnrollmentDetailDTO d : enrollmentDTO.details()) {
            Student student = studentService.findById(enrollmentDTO.studentId());
            Course course = courseService.findByName(d.courseName());
            enrollmentList.add(new Enrollment(null,localDateTime,student,course,d.classroom(),enrollmentDTO.status()));
        }

        return enrollmentList;
    }

    private ResponseEnrollmentDTO convertToDto(List<Enrollment> enrollments) {
        List<RequestEnrollmentDetailDTO> details = new ArrayList<>();
        LocalDateTime localDateTime = enrollments.getFirst().getEnrollmentDateTime();
        Integer studentId = enrollments.getFirst().getStudent().getId();
        Boolean status = enrollments.getFirst().isStatus();
        for (Enrollment e : enrollments) {
            details.add(new RequestEnrollmentDetailDTO(e.getCourse().getCourseName(),e.getClassroom()));
        }
        return new ResponseEnrollmentDTO(localDateTime,studentId,details,status);
    }
}

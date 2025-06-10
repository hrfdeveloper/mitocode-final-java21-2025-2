package com.mitocode.academy.controller;

import com.mitocode.academy.dto.GenericResponse;
import com.mitocode.academy.dto.RequestStudentCreateDTO;
import com.mitocode.academy.dto.RequestStudentUpdateDTO;
import com.mitocode.academy.dto.ResponseStudentDTO;
import com.mitocode.academy.model.Student;
import com.mitocode.academy.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<GenericResponse<ResponseStudentDTO>> findAll(@RequestParam(name = "sortedBy",required = false) String sortedBy) throws Exception {
        List<ResponseStudentDTO> list = new ArrayList<>();
        Comparator<Student> compareByAge = (s1, s2) -> {
            if (Objects.equals(s1.getAge(), s2.getAge())) {
                return s1.getId() - s2.getId();
            } else {
                return s1.getAge() - s2.getAge();
            }

        };
        Comparator<Student> compareById = Comparator.comparingInt(Student::getId);

        if ((sortedBy != null) && (sortedBy.equals("age"))) {
            list = studentService.findAll()
                    .stream()
                    .sorted(compareByAge)
                    .map(this::convertToDto)
                    .toList();
        } else {
            list = studentService.findAll()
                    .stream()
                    .sorted(compareById)
                    .map(this::convertToDto)
                    .toList();
        }
        //Comparator.
        return ResponseEntity.ok(new GenericResponse<>(200, "success", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ResponseStudentDTO>> findById(@PathVariable("id") Integer id) throws Exception {
        Student student = studentService.findById(id);
        return ResponseEntity.ok(new GenericResponse<>(200, "success", List.of(convertToDto(student))));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<ResponseStudentDTO>> create(@Valid @RequestBody RequestStudentCreateDTO studentDTO) throws Exception {
        Student student = studentService.save(convertToModel(studentDTO));
        return ResponseEntity.ok(new GenericResponse<>(201, "created", List.of(convertToDto(student))));
    }

    @PutMapping
    public ResponseEntity<GenericResponse<ResponseStudentDTO>> update(@Valid @RequestBody RequestStudentUpdateDTO studentDTO) throws Exception {
        Student student = studentService.update(studentDTO.id(), convertToModel(studentDTO));
        return ResponseEntity.ok(new GenericResponse<>(200, "updated", List.of(convertToDto(student))));
    }

    private Student convertToModel(RequestStudentCreateDTO studentDTO) {
        return new Student(null,
                            studentDTO.firstName(),
                            studentDTO.lastName(),
                            studentDTO.DNI(),
                            studentDTO.age() );
    }

    private Student convertToModel(RequestStudentUpdateDTO studentDTO) {
        return new Student(studentDTO.id(),
                studentDTO.firstName(),
                studentDTO.lastName(),
                studentDTO.DNI(),
                studentDTO.age() );
    }

    private ResponseStudentDTO convertToDto(Student student) {
        return new ResponseStudentDTO(student.getId(),
                                    student.getFirstName(),
                                    student.getLastName(),
                                    student.getDNI(),
                                    student.getAge() );
    }
}

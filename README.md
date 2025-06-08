# mitocode-final-java21-2025-2
Final project for Java21 Backend Course - Mitocode 2025-2
```
@Author : Henry Ramírez Fano
```

## Assignments
Create REST services for:

### • CRUD Student
    o Id: integer
    o First Name: string
    o Last Name: string
    o DNI: string
    o Age: int
### • CRUD Course
    o Id: integer
    o Name: string
    o Acronym: string
    o Status: boolean
### • Enrollment

    ▪ Enrollment date: LocalDateTime
    ▪ Student: Class
    ▪ Enrollment details: Class[] Array
    ▪ Status: boolean

    o Enrollment details
      ▪ Course: Class
      ▪ Classroom: String
### • List students sorted by age using functional programming
### • Display the relationship of enrolled courses and their corresponding students using functional programming (hint: use a Map<K,V>)
    o Example:
        ▪ Programación (Course)
            • Jaime Medina (Student)
        ▪ Base de Datos (Course)
            • Mito X  (Student)
            • Code Y  (Student)
---
## Considerations
```
- Use any RDBMS of your preference
- Use global exception control
- Validate service requests using @Valid
- Use of Spring Security and JWT is not mandatory
```
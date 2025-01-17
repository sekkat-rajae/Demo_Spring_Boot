package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class studentService {
    private final studentRepository studentRepository;
    @Autowired
    public studentService(com.example.demo.student.studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<student> getStudents(){
        return studentRepository.findAll();
    }


    public void AddNewStudent(student student) {
        Optional<student> studentByEmail = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "Student with id"+ studentId + "doesn't exist");
        }
        studentRepository.deleteById(studentId);
    }

    // the transactional annotation here helps to use directly the setters methods to male changes in our database
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id"+ studentId + "doesn't exist"
                ));

        if(name!=null &&
                name.length()>0 &&
                !Objects.equals(student.getName(), name)){
                    student.setName(name);
        }
        if(email!=null &&
                email.length()>0 &&
                !Objects.equals(student.getEmail(), email)){

            Optional<student> studentByEmail = studentRepository
                    .findStudentByEmail(student.getEmail());
            if (studentByEmail.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }
}

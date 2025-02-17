package com.demo.Learning.Navigator.Controller;

import com.demo.Learning.Navigator.Entity.Student;
import com.demo.Learning.Navigator.Entity.StudentDTO;
import com.demo.Learning.Navigator.Repocitory.StudentsRepocitory;
import com.demo.Learning.Navigator.Service.StudentsService;
import com.demo.Learning.Navigator.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    StudentsService studentsService;
    @Autowired
    StudentsRepocitory studentsRepocitory;

    @PostMapping("/save")
    public ResponseEntity saveStudent(@RequestBody StudentDTO student) {
        try {
            if (Objects.nonNull(student)) {
                return new ResponseEntity<>(studentsService.saveStudent(student),HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Student data Cannot be null", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PutMapping("/enroll")
    public ResponseEntity<?> EnrollExam(@RequestBody StudentDTO student, @RequestParam("studentId") Integer stdentId) {
        try {
            Optional<Student> student1 = studentsRepocitory.findById(stdentId);
            if (!student1.isPresent()) {
                return new ResponseEntity<>("No Student found with id" + stdentId, HttpStatus.NOT_FOUND);
            }
            if (Objects.nonNull(student)) {
                return new ResponseEntity<>(studentsService.EnrollExam(student, stdentId), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Student data Cannot be null" + stdentId, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PutMapping("/updateStudent")
    public ResponseEntity<?> UpdateStudent(@RequestBody StudentDTO student, @RequestParam("studentId") Integer stdentId) {
        try {
            Optional<Student> student1 = studentsRepocitory.findById(stdentId);
            if (!student1.isPresent()) {
                return new ResponseEntity<>("No Student found with id" + stdentId, HttpStatus.NOT_FOUND);
            }
            if (Objects.nonNull(student)) {
                return new ResponseEntity<>(studentsService.updateStudent(student, stdentId), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student data Cannot be null" + stdentId, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }

        @DeleteMapping("/deleteStudent")
    public ResponseEntity<?> deleteStudent(@RequestParam("stdentId") Integer stdentId) {
        try {

            Optional<Student> student1 = studentsRepocitory.findById(stdentId);
            if (!student1.isPresent()) {
                return new ResponseEntity<>("No Student found with id" + stdentId, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(studentsService.deleteStudent(stdentId), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }
    @GetMapping("/list")
    public ResponseEntity<?>getStudentsList(){
        return new ResponseEntity<>(studentsService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/getById/{studentId}")
    public ResponseEntity<?>getById(@PathVariable Integer studentId){
        try {
            return new ResponseEntity<>(studentsService.finfById(studentId),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }

}

package com.demo.Learning.Navigator.Controller;

import com.demo.Learning.Navigator.Entity.Subject;
import com.demo.Learning.Navigator.Entity.SubjectDTO;
import com.demo.Learning.Navigator.Repocitory.SubjectRepocitory;
import com.demo.Learning.Navigator.Service.SubjectsService;
import com.demo.Learning.Navigator.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
    @Autowired
    private SubjectsService subjectsService;
    @Autowired
    private SubjectRepocitory subjectRepocitory;
    @PostMapping("/save")
    public ResponseEntity<?> saveSubject(@RequestBody SubjectDTO subjectDTO) {
        try {
            if (Objects.nonNull(subjectDTO)) {
                return new ResponseEntity<>(subjectsService.createSUbject(subjectDTO), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new CustomException("Subject Cannot be null"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?>updateSubject(@RequestBody SubjectDTO subjectDTO,@RequestParam("subjectId") Integer subjectId){
        try {
            Optional<Subject>subject=subjectRepocitory.findById(subjectId);
            if(Objects.nonNull(subject)){
            return new ResponseEntity<>(subjectsService.updateSubject(subjectDTO,subjectId),HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(new CustomException("No Subject Found with id"+subjectId),HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
    @DeleteMapping("/delete")
    public  ResponseEntity<?> deleteSubject(@RequestParam("subjectId") Integer subjectId) {
        try {
            Optional<Subject> subject = subjectRepocitory.findById(subjectId);
            if (Objects.nonNull(subject)) {
                return new ResponseEntity<>(subjectsService.deleteSubject(subjectId), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(new CustomException("No Subject Found with id" + subjectId), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

        }
    }
    @GetMapping("/list")
    public ResponseEntity<?>getAllSubjects(){
        return  new ResponseEntity<>(subjectsService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/findById/{subjectId}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer subjectId){
        try {
            return new ResponseEntity<>(subjectsService.findById(subjectId),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}

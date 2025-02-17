package com.demo.Learning.Navigator.Controller;

import com.demo.Learning.Navigator.Entity.Exam;
import com.demo.Learning.Navigator.Entity.ExamDTO;
import com.demo.Learning.Navigator.Repocitory.ExamRepocitory;
import com.demo.Learning.Navigator.Service.ExamsService;
import com.demo.Learning.Navigator.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exam")
public class ExamController {
    @Autowired
    private ExamsService examsService;
    @Autowired
    ExamRepocitory examRepocitory;
    @PostMapping("/save")
    public ResponseEntity<?> createExam(@RequestBody ExamDTO examDTO) {
        try {
            if (Objects.nonNull(examDTO)) {
                return new ResponseEntity<>(examsService.creatExam(examDTO), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Exam cannot be null",HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?>updateExam(@RequestBody ExamDTO examDTO,@RequestParam("examId")Integer examId){
        try {
            Optional<Exam> exam=examRepocitory.findById(examId);
            if(exam.isPresent()){
                return new ResponseEntity<>(examsService.updateExam(examDTO,examId),HttpStatus.OK);
            }
            return new ResponseEntity<>(new CustomException("No Exam Found With id"+examId),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }
    @DeleteMapping("/deleExam")
    public ResponseEntity<?> deleteExam(@RequestParam("examId") Integer examId){
        Optional<Exam> exam=examRepocitory.findById(examId);
        if(exam.isPresent()){
            return new ResponseEntity<>(examsService.deleteExam(examId),HttpStatus.OK);
        }
        return new ResponseEntity<>("No Exam Found With id"+examId,HttpStatus.NOT_FOUND);
    }
    @GetMapping("/list")
    public ResponseEntity<?>getExamList(){
        return new ResponseEntity<>(examsService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/findById/{examId}")
    public ResponseEntity<?>findById(@PathVariable Integer examId){
        try {
            return  new ResponseEntity<>(examsService.findById(examId),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

}

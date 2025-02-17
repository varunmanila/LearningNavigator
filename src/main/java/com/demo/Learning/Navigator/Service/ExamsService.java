package com.demo.Learning.Navigator.Service;

import com.demo.Learning.Navigator.Entity.Exam;
import com.demo.Learning.Navigator.Entity.ExamDTO;
import com.demo.Learning.Navigator.Entity.Student;
import com.demo.Learning.Navigator.Entity.Subject;
import com.demo.Learning.Navigator.Repocitory.ExamRepocitory;
import com.demo.Learning.Navigator.Repocitory.StudentsRepocitory;
import com.demo.Learning.Navigator.Repocitory.SubjectRepocitory;
import com.demo.Learning.Navigator.Utils.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExamsService {
    @Autowired
    SubjectRepocitory subjectRepocitory;
    @Autowired
    StudentsRepocitory studentsRepocitory;
    @Autowired
    ExamRepocitory examRepocitory;

    @Transactional
    public Exam creatExam(ExamDTO examDTO) {
        Exam exam = new Exam();

        if (Objects.nonNull(examDTO.getSubjectId())) {
            Optional<Subject> subjectOptional = subjectRepocitory.findById(examDTO.getSubjectId());
            if (subjectOptional.isPresent()) {
                exam.setSubject(subjectOptional.get());
            } else {
                throw new CustomException("Invalid Subject Id");
            }
        } else {
            throw new CustomException("Subject Cannot be Null");
        }

        if (Objects.nonNull(examDTO.getStudentIds())) {
            List<Student> studentList = studentsRepocitory.findAllById(examDTO.getStudentIds());
            exam.setStudentList(studentList);
        }

        return examRepocitory.save(exam);
    }


    public Exam updateExam(ExamDTO examDTO, Integer examId) {
        Optional<Exam> exam=examRepocitory.findById(examId);
        if(exam.isPresent()){
            if(Objects.nonNull(examDTO.getStudentIds())){
              Optional<Subject> subject=subjectRepocitory.findById(examDTO.getSubjectId());
              exam.get().setSubject(subject.get());
            }
            if(Objects.nonNull(examDTO.getStudentIds())){
                List<Student>studentList=exam.get().getStudentList();
                List<Student>students=studentsRepocitory.findAllById(examDTO.getStudentIds());
                studentList.addAll(students);
                exam.get().setStudentList(studentList);
            }
            return examRepocitory.save(exam.get());
        }
        throw  new RuntimeException("No Exam Found with id"+examId);
    }

    public String deleteExam(Integer examId) {
        Optional<Exam> exam=examRepocitory.findById(examId);
        if(exam.isPresent()){
            examRepocitory.delete(exam.get());
          return  "Success";
        }throw  new RuntimeException("No Exam Found with id"+examId);
    }

    public List<Exam> findAll() {
        return examRepocitory.findAll();
    }

    public Exam findById(Integer examId) {
        Optional<Exam> exam=examRepocitory.findById(examId);
        if(exam.isPresent()){
           return exam.get();
        }
        throw  new RuntimeException("No Exam Found with id"+examId);
    }
}

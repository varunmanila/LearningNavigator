package com.demo.Learning.Navigator.Service;

import com.demo.Learning.Navigator.Entity.Exam;
import com.demo.Learning.Navigator.Entity.Student;
import com.demo.Learning.Navigator.Entity.Subject;
import com.demo.Learning.Navigator.Entity.SubjectDTO;
import com.demo.Learning.Navigator.Repocitory.ExamRepocitory;
import com.demo.Learning.Navigator.Repocitory.StudentsRepocitory;
import com.demo.Learning.Navigator.Repocitory.SubjectRepocitory;
import com.demo.Learning.Navigator.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubjectsService {
    @Autowired
    SubjectRepocitory subjectRepocitory;
    @Autowired
    StudentsRepocitory studentsRepocitory;
    @Autowired
    ExamRepocitory examRepocitory;

    public Subject createSUbject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setName(subjectDTO.getName());
        if (Objects.nonNull(subjectDTO.getStudentIds()) && !subjectDTO.getStudentIds().isEmpty()) {
            List<Student> studentList = studentsRepocitory.findAllById(subjectDTO.getStudentIds());
            subject.setStudentList(studentList);
        }
        if (Objects.nonNull(subjectDTO.getExamIds())&& !subjectDTO.getExamIds().isEmpty() ) {
            List<Exam> examList = examRepocitory.findAllById(subjectDTO.getExamIds());
            subject.setExamList(examList);
        }
        Subject subject1=subjectRepocitory.save(subject);

        return subject1;
    }

    public Subject updateSubject(SubjectDTO subjectDTO, Integer subjectId) {
      Optional<Subject> subject=subjectRepocitory.findById(subjectId);
      if(subject.isPresent()){
          if(Objects.nonNull(subjectDTO.getName())){
              subject.get().setName(subjectDTO.getName());
          }
          if(Objects.nonNull(subjectDTO.getExamIds())){
              List<Exam>exams=subject.get().getExamList();
              List<Exam>examList=examRepocitory.findAllById(subjectDTO.getExamIds());
              exams.addAll(examList);
              subject.get().setExamList(exams);
          }
          if(Objects.nonNull(subjectDTO.getStudentIds())){
              List<Student>studentList=subject.get().getStudentList();
              List<Student>students=studentsRepocitory.findAllById(subjectDTO.getStudentIds());
              studentList.addAll(students);
              subject.get().setStudentList(studentList);
          }
          return   subjectRepocitory.save(subject.get());
        }
        throw new CustomException( "Error While Updating Subject with Id"+subjectId);
    }

    public String deleteSubject(Integer subjectId) {
        Optional<Subject> subject=subjectRepocitory.findById(subjectId);
        if(subject.isPresent()){
            subjectRepocitory.delete(subject.get());
            return "Subject With id"+subjectId+"is Deleted Successfully";
        }
        return "Error While Deleting Subject with Id"+subjectId;
    }

    public List<Subject> findAll() {
       return subjectRepocitory.findAll();
    }

    public Subject findById(Integer subjectId) {
        Optional<Subject> subject=subjectRepocitory.findById(subjectId);
        if(subject.isPresent()){
            return subject.get();
        }
       throw new CustomException("Subject is not found with id"+subjectId);
    }
}

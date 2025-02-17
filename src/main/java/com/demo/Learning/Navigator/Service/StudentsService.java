package com.demo.Learning.Navigator.Service;

import com.demo.Learning.Navigator.Entity.Exam;
import com.demo.Learning.Navigator.Entity.Student;
import com.demo.Learning.Navigator.Entity.StudentDTO;
import com.demo.Learning.Navigator.Entity.Subject;
import com.demo.Learning.Navigator.Repocitory.ExamRepocitory;
import com.demo.Learning.Navigator.Repocitory.StudentsRepocitory;
import com.demo.Learning.Navigator.Repocitory.SubjectRepocitory;
import com.demo.Learning.Navigator.Utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentsService {
    @Autowired
    private StudentsRepocitory studentsRepocitory;
    @Autowired
    private SubjectRepocitory subjectRepocitory;
    @Autowired
    private ExamRepocitory examRepocitory;

    public Student saveStudent(StudentDTO student) {
        try {
            Student student1 = new Student();
            student1.setName(student.getName());
            if (Objects.nonNull(student.getSubjectId())) {
                List<Subject> subject = subjectRepocitory.findAllById(student.getSubjectId());
                if (subject.size() > 0) {
                    student1.setSubjectList(subject);
                    if (Objects.nonNull(student.getExamId())) {
                        List<Exam> examList = examRepocitory.findAllById(student.getExamId());
                        if (examList.size() > 0) {
                            student1.setExam(examList);
                        }
                    }
                }
            }

            return studentsRepocitory.save(student1);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public Student EnrollExam(StudentDTO studentDto, Integer studentId) {
        Optional<Student> student = studentsRepocitory.findById(studentId);
        if (student.isPresent()) {
            if(Objects.nonNull(studentDto.getSubjectId())){
                Optional<Subject>subject=subjectRepocitory.findById(studentDto.getSubjectId().get(0));
                if(subject.isPresent()){
                    List<Exam> exam = examRepocitory.findAllById(studentDto.getExamId());
                    student.get().setExam(exam);
                }
            }else  if (student.get().getSubjectList().size() > 0 && Objects.nonNull(studentDto.getExamId())) {
                List<Exam> exam = examRepocitory.findAllById(studentDto.getExamId());
                student.get().setExam(exam);
            }
            return studentsRepocitory.save(student.get());
        } else {
            throw new CustomException("No Student Found With id" + studentId);
        }
    }


    public Student updateStudent(StudentDTO studentDtO, Integer stdentId) {
        Optional<Student> student = studentsRepocitory.findById(stdentId);
        if (student.isPresent()) {
            if (Objects.nonNull(studentDtO.getSubjectId())) {
                List<Subject> subjectList = subjectRepocitory.findByIdIn(studentDtO.getSubjectId());
                if (subjectList.size() > 0) {
                    List<Subject> subjects = student.get().getSubjectList();
                    subjects.addAll(subjectList);
                    student.get().setSubjectList(subjects);
                }
            }
            if (student.get().getSubjectList().size() > 0 && Objects.nonNull(studentDtO.getExamId())) {
                List<Exam> examList = student.get().getExam();
                List<Exam> exam = examRepocitory.findAllById(studentDtO.getExamId());
                examList.addAll(exam);
                student.get().setExam(examList);
            }
            return studentsRepocitory.save(student.get());

        } else {
            throw new CustomException("No Student Found With id" + stdentId);
        }
    }

    public String deleteStudent(Integer stdentId) {
        Optional<Student> student = studentsRepocitory.findById(stdentId);
        if (student.isPresent()) {
            studentsRepocitory.delete(student.get());
            return "Success";
        }
        return "Error While Deleting Student With Id" + stdentId;
    }

    public List<Student> findAll() {
        return studentsRepocitory.findAll();
    }

    public Student finfById(Integer stdentId) {
        try {
            Optional<Student> student = studentsRepocitory.findById(stdentId);
            if (student.isPresent()) {
                return student.get();

            }
        }catch (Exception e){
         throw new CustomException("No user Found With id"+stdentId);
        }
        return null;
    }
}

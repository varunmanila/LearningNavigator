package com.demo.Learning.Navigator.Repocitory;

import com.demo.Learning.Navigator.Entity.Exam;
import com.demo.Learning.Navigator.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepocitory extends JpaRepository<Exam,Integer> {
//    @Query("SELECT e FROM Exam e JOIN e.studentList s WHERE s.id = :studentId")
//    List<Exam> findByStudentId(@Param("studentId") Integer studentId);
}

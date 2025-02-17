package com.demo.Learning.Navigator.Repocitory;

import com.demo.Learning.Navigator.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepocitory extends JpaRepository<Subject,Integer> {
    List<Subject> findByIdIn(List<Integer> subjectId);
}

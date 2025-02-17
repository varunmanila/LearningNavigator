package com.demo.Learning.Navigator.Repocitory;

import com.demo.Learning.Navigator.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepocitory extends JpaRepository<Student,Integer> {
}

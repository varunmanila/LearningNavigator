package com.demo.Learning.Navigator.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tblsubject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Integer id;

    @Column(name = "name")
    private String name;
    @JsonBackReference
    @ManyToMany(mappedBy = "subjectList")
    private List<Student> studentList = new ArrayList<>();
    @JsonBackReference
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Exam> examList = new ArrayList<>();
}

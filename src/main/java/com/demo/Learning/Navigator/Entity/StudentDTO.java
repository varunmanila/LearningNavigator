package com.demo.Learning.Navigator.Entity;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private String name;
    private List<Integer> subjectId;
    private List<Integer> examId;
}

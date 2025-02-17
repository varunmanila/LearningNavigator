package com.demo.Learning.Navigator.Entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectDTO {
    private String name;
    private List<Integer> studentIds;
    private List<Integer> examIds;

}

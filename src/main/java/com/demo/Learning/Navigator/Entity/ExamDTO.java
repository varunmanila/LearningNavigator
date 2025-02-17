package com.demo.Learning.Navigator.Entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.List;
@Data
public class ExamDTO {
private Integer subjectId;
private List<Integer> studentIds;
}

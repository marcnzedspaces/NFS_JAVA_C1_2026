package com.example.demoapi.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import org.springframework.stereotype.Service;

import com.example.demoapi.dto.CourseCategorySummary;

@Service
public class CourseReportService {
    private final MongoTemplate mongoTemplate;

    public CourseReportService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<CourseCategorySummary> countCoursesByCategory() { 
        Aggregation aggregation = newAggregation(
            group("category").count().as("totalCourses"),
            project("totalCourses").and("_id").as("category"),
            sort(Sort.Direction.ASC, "category")
        );
        
        return mongoTemplate
        .aggregate(aggregation, "courses", CourseCategorySummary.class)
        .getMappedResults();
    } 
}

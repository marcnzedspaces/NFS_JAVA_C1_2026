package com.example.demoapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoapi.dto.CourseCategorySummary;
import com.example.demoapi.service.CourseReportService;

@RestController
@RequestMapping("/api/v1/reports/courses")
public class CourseReportController {
    private final CourseReportService courseReportService;

    public CourseReportController(CourseReportService courseReportService) {
        this.courseReportService = courseReportService;
    }

    @GetMapping("/by-category")
    public List<CourseCategorySummary> countCoursesByCategory() {
        return courseReportService.countCoursesByCategory();
    }
}

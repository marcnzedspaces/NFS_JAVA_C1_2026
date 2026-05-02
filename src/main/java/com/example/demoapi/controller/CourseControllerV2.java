package com.example.demoapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoapi.dto.CourseResponseV2;
import com.example.demoapi.model.Course;
import com.example.demoapi.service.CourseService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v2/courses")
public class CourseControllerV2 {
    private final CourseService courseService;

    public CourseControllerV2(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponseV2> getAllCoursesV2() {
        return courseService.getAllCourses()
        .stream()
        .map(this::mapToResponseV2)
        .toList();
    }

    @GetMapping("/{id}")
    public CourseResponseV2 getCourseByIdV2(@PathVariable String id) {
        Course course = courseService.getCourseById(id);
        return mapToResponseV2(course);
    }

    private CourseResponseV2 mapToResponseV2(Course course){
        String publicationStatus = course.isPublished() ? "published" : "unpublished";

        String summary = course.getTitle()
        + " is a "
        + course.getCategory()
        + " course with "
        + course.getDuration()
        + " hours of content.";
        
        return new CourseResponseV2(
            course.getId(),
            course.getTitle(),
            course.getCategory(),
            course.getDuration(),
            publicationStatus,
            summary
        );
    }
    
}

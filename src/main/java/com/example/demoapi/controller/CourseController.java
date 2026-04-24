package com.example.demoapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demoapi.dto.CreateCourseRequest;
import com.example.demoapi.model.Course;
import com.example.demoapi.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course createCourse(@Valid @RequestBody CreateCourseRequest request) {
        Course course = new Course(
                request.getTitle(),
                request.getCategory(),
                request.getDuration(),
                request.isPublished()
        );
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable String id, @Valid @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/category/{category}")
    public List<Course> getCoursesByCategory(@PathVariable String category) {
        return courseService.getCoursesByCategory(category);
    }

    @GetMapping("/published/{published}")
    public List<Course> getCourseByPublished(@PathVariable boolean published) {
        return courseService.getCoursesByPublished(published);
    }

    @GetMapping("/min-duration/{duration}")
    public List<Course> getCourseByMinimumDuration(@PathVariable int duration) {
        return courseService.getCoursesByMinimumDuration(duration);
    }
}
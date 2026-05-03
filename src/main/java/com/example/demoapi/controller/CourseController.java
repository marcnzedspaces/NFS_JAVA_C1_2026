package com.example.demoapi.controller;

import com.example.demoapi.model.Course;
import com.example.demoapi.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/courses")
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
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable String id, @RequestBody Course course) {
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
    public List<Course> getCoursesByPublished(@PathVariable boolean published) {
        return courseService.getCoursesByPublished(published);
    }

    @GetMapping("/min-duration/{duration}")
    public List<Course> getCoursesByMinimumDuration(@PathVariable int duration) {
        return courseService.getCoursesByMinimumDuration(duration);
    }

    @GetMapping("/search")
    public List<Course> searchCoursesByTitle(@RequestParam String keyword) {
        return courseService.searchCoursesByTitle(keyword);
    }

    @GetMapping("/category/{category}/published/{published}")
    public List<Course> getCoursesByCategoryAndPublished(
            @PathVariable String category,
            @PathVariable boolean published) {
        return courseService.getCoursesByCategoryAndPublished(category, published);
    }

    @GetMapping("/paged")
    public Page<Course> getPagedCourses(Pageable pageable) {
        return courseService.getPagedCourses(pageable);
    }

    @GetMapping("/paged/category/{category}")
    public Page<Course> getPagedCoursesByCategory(@PathVariable String category, Pageable pageable) {
        return courseService.getPagedCoursesByCategory(category, pageable);
    }

    @GetMapping("/paged/published/{published}")
    public Page<Course> getPagedCoursesByPublished(@PathVariable boolean published, Pageable pageable) {
        return courseService.getPagedCoursesByPublished(published, pageable);
    }

    @GetMapping("/paged/search")
    public Page<Course> searchPagedCoursesByTitle(@RequestParam String keyword, Pageable pageable) {
        return courseService.searchPagedCoursesByTitle(keyword, pageable);
    }

    @GetMapping("/paged/category/{category}/published/{published}")
    public Page<Course> getPagedCoursesByCategoryAndPublished(
            @PathVariable String category,
            @PathVariable boolean published,
            Pageable pageable) {
        return courseService.getPagedCoursesByCategoryAndPublished(category, published, pageable);
    }
}
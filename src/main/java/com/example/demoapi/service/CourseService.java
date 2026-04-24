package com.example.demoapi.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demoapi.model.Course;
import com.example.demoapi.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        return courseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(String id, Course updateCourse) {
        Course existingCourse = getCourseById(id);
        existingCourse.setTitle(updateCourse.getTitle());
        existingCourse.setCategory(updateCourse.getCategory());
        existingCourse.setDuration(updateCourse.getDuration());
        existingCourse.setPublished(updateCourse.isPublished());
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public List<Course> getCoursesByPublished(boolean published) {
        return courseRepository.findByPublished(published);
    }

    public List<Course> getCoursesByMinimumDuration(int duration) {
        return courseRepository.findByDurationGreaterThanEqual(duration);
    }
}

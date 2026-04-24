package com.example.demoapi.service;

import com.example.demoapi.model.Course;
import com.example.demoapi.repository.CourseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        log.info("Fetching all courses");
        List<Course> courses = courseRepository.findAll();
        log.info("Returned {} courses", courses.size());
        return courses;
    }

    public Course getCourseById(String id) {
        log.info("Fetching course by id={}", id);
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: "));
    }


    public Course createCourse(Course course) {
        log.info("Creating course with title: '{}'", course.getTitle());
        return courseRepository.save(course);
    }

    public Course updateCourse(String id, Course updatedCourse) {
        log.info("Updating course id={}", id);

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id));
        
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setCategory(updatedCourse.getCategory());
        existingCourse.setDuration(updatedCourse.getDuration());
        existingCourse.setPublished(updatedCourse.isPublished());
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(String id) {
        log.info("Deleting course id={}", id);
        if(!courseRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    public List<Course> getCoursesByCategory(String category) {
        log.info("Fetching course by category={}", category);
        return courseRepository.findByCategory(category);
    }

    public List<Course> getCoursesByPublished(boolean published) {
        log.info("Fetching course by published={}", published);
        return courseRepository.findByPublished(published);
    }

    public List<Course> getCoursesByMinimumDuration(int duration) {
        log.info("Fetching course by duration={}", duration);
        return courseRepository.findByDurationGreaterThanEqual(duration);
    }

    public List<Course> searchCoursesByTitle(String keyword) {
        log.info("Searching courses by keyword={}", keyword);
        List<Course> results = courseRepository.findByTitleContainingIgnoreCase(keyword);
        log.info("Found {} courses for keyword '{}'", results.size(), keyword);
        return results;
    }

    public List<Course> getCoursesByCategoryAndPublished(String category, boolean published) {
        log.info("Fetching course by category='{}' and published='{}'", category, published);
        return courseRepository.findByCategoryAndPublished(category, published);
    }

    public Page<Course> getPagedCourses(Pageable pageable) {
        log.info("Fetching paged courses: page={}, size={}, published={}", 
                pageable.getPageNumber(), 
                pageable.getPageSize(), 
                pageable.isPaged());

        Page<Course> result = courseRepository.findAll(pageable);
        log.info("Returned {} courses on page {} of {}", 
                result.getNumberOfElements(), 
                result.getNumber(), 
                result.getTotalPages());
        return result;
    }

    public Page<Course> getPagedCoursesByCategory(String category, Pageable pageable) {
        log.info("Fetching paged courses by category: page={}, size={}, sort={}", 
                pageable.getPageNumber(), 
                pageable.getPageSize(), 
                pageable.getSort());

        Page<Course> result = courseRepository.findByCategory(category, pageable);
        log.info("Returned {} courses for category '{}'", 
                result.getNumberOfElements(), 
                category);
        return result;
    }

    public Page<Course> getPagedCoursesByPublished(boolean published, Pageable pageable) {
        log.info("Fetching paged courses by published: page={}, size={}, sort={}", 
                published,
                pageable.getPageNumber(), 
                pageable.getPageSize(), 
                pageable.getSort());

        Page<Course> result = courseRepository.findByPublished(published, pageable);
        log.info("Returned {} courses", result.getNumberOfElements());
        return result;
    }

    public Page<Course> searchPagedCoursesByTitle(String keyword, Pageable pageable) {
        log.info("Fetching paged courses by keyword='{}': page={}, size={}, sort={}", 
                keyword,
                pageable.getPageNumber(), 
                pageable.getPageSize(), 
                pageable.getSort());

        Page<Course> result = courseRepository.findByTitleContainingIgnoreCase(keyword, pageable);
        log.info("Returned {} matching courses", result.getNumberOfElements());
        return result;
    }

    public Page<Course> getPagedCoursesByCategoryAndPublished(String category, boolean published, Pageable pageable) {
        log.info("Fetching paged courses by category='{}' and published='{}': page={}, size={}, sort={}", 
                category,
                published,
                pageable.getPageNumber(), 
                pageable.getPageSize(), 
                pageable.getSort());

        Page<Course> result = courseRepository.findByCategoryAndPublished(category, published, pageable);
        log.info("Returned {} matching courses", result.getNumberOfElements());
        return result;
    }
}
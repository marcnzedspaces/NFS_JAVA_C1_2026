package com.example.demoapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demoapi.model.Course;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByCategory(String category);

    List<Course> findByPublished(boolean published);

    List<Course> findByTitleContaining(String title);

    List<Course> findByDurationGreaterThanEqual(int duration);
}

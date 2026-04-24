package com.example.demoapi.repository;

import com.example.demoapi.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {

    List<Course> findByCategory(String category);

    List<Course> findByPublished(boolean published);

    List<Course> findByDurationGreaterThanEqual(int duration);

    List<Course> findByTitleContainingIgnoreCase(String keyword);

    List<Course> findByCategoryAndPublished(String category, boolean published);

    Page<Course> findAll(Pageable pageable);

    Page<Course> findByCategory(String category, Pageable pageable);

    Page<Course> findByPublished(boolean published, Pageable pageable);

    Page<Course> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Course> findByCategoryAndPublished(String category, boolean published, Pageable pageable);
}
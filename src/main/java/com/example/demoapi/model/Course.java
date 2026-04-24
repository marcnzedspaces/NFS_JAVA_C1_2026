package com.example.demoapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "courses")
public class Course {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // prevent Postman/request from setting id
    private String id;
    private String title;
    private String category;
    private int duration;
    private boolean published;

    public Course(){

    }
    public Course(String title, String category, int duration, boolean published) {
        this.title = title;
        this.category = category;
        this.duration = duration;
        this.published = published;
    }

    // getters and setters for course properties
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

}

package com.example.demoapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateCourseRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 1, message = "Duration must be at least 1 hour")
    private int duration;

    private boolean published;

    public CreateCourseRequest() {
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isPublished() {
        return published;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
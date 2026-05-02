package com.example.demoapi.dto;

public class CourseCategorySummary {
    private String category;
    private String totalCourses;

    public CourseCategorySummary() {
    }

    public CourseCategorySummary(String category, String totalCourses) {
        this.category = category;
        this.totalCourses = totalCourses;
    }

    public String getCategory() {
        return category;
    }

    public String getTotalCourses() {
        return totalCourses;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTotalCourses(String totalCourses) {
        this.totalCourses = totalCourses;
    }
}

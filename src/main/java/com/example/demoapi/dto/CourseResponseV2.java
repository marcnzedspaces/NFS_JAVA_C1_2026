package com.example.demoapi.dto;

public class CourseResponseV2 {
    private String id;
    private String courseTitle;
    private String categoryName;
    private int durationHours;
    private String publicationStatus;
    private String summary;

    public CourseResponseV2() {
    }

    public CourseResponseV2(String id, String courseTitle, String categoryName, int durationHours, String publicationStatus, String summary) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.categoryName = categoryName;
        this.durationHours = durationHours;
        this.publicationStatus = publicationStatus;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}

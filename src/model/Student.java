package model;

import java.util.List;

public class Student extends User{

    //Attributes for a student
    private List<Course> enrolledCourses;
    private String progress; /*------>*/

    //Constructor
    public Student(String userId, String role, String username, String email, String passwordHash, List<Course> enrolledCourses, String progress) {
        this.setUserId(userId);
        this.setRole(role);
        this.setUsername(username);
        this.setEmail(email);
        this.setPasswordHash(passwordHash);
        this.enrolledCourses = enrolledCourses;
        this.progress = "0%";
    }

    //Getters & Setters
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }


}

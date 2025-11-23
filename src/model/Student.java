package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Student extends User {

    private List<Course> enrolledCourses;
    private String progress;
    private Map<String, List<QuizResult>> quizAttempts = new HashMap<>(); // درسId -> قائمة المحاولات

    public Student(String userId, String role, String username, String email, String passwordHash, List<Course> enrolledCourses, String progress) {
        this.setUserId(userId);
        this.setRole(role);
        this.setUsername(username);
        this.setEmail(email);
        this.setPasswordHash(passwordHash);
        this.enrolledCourses = enrolledCourses;
        this.progress = progress != null ? progress : "0%";
        this.quizAttempts = new HashMap<>();
    }

    // Getters & Setters
    public List<Course> getEnrolledCourses() { return enrolledCourses; }
    public void setEnrolledCourses(List<Course> enrolledCourses) { this.enrolledCourses = enrolledCourses; }

    public String getProgress() { return progress; }
    public void setProgress(String progress) { this.progress = progress; }

    public Map<String, List<QuizResult>> getQuizAttempts() { return quizAttempts; }

    public List<QuizResult> getQuizAttempts(String lessonId) {
        return quizAttempts.getOrDefault(lessonId, new ArrayList<>());
    }

    public void addQuizAttempt(String lessonId, QuizResult result) {
        quizAttempts.computeIfAbsent(lessonId, k -> new ArrayList<>()).add(result);
    }
}

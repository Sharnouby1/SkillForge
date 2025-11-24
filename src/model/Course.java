package model;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseID;
    private String title;
    private String description;
    private String instructorID;
    private List<Student> students;

    public Course(String courseID, String title, String description, String instructorID) {
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.instructorID = instructorID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void setEnrolledStudentIDs(List<String> enrolledStudentIDs) {
        this.enrolledStudentIDs = enrolledStudentIDs;
    }

    public void setAverageCourseScore(double averageCourseScore) {
        this.averageCourseScore = averageCourseScore;
    }

    public void setTotalCompletions(int totalCompletions) {
        this.totalCompletions = totalCompletions;
    }

    // All lessons in this course
    private List<Lesson> lessons = new ArrayList<>();

    // All enrolled students (store only IDs)
    private List<String> enrolledStudentIDs = new ArrayList<>();

    // Analytics fields
    private double averageCourseScore = 0.0;
    private int totalCompletions = 0;

    public Course(String courseID, String title, String description, String instructorID,List<Student> students, List<Lesson> lessons) {
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.instructorID = instructorID;
        this.students = students;
        this.lessons = lessons;
    }

    // ---------------- Getters -----------------

    public String getCourseID() {
        return courseID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<String> getEnrolledStudentIDs() {
        return enrolledStudentIDs;
    }

    public double getAverageCourseScore() {
        return averageCourseScore;
    }

    public int getTotalCompletions() {
        return totalCompletions;
    }

    // ---------------- Core Logic -----------------

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void enrollStudent(String studentID) {
        if (!enrolledStudentIDs.contains(studentID)) {
            enrolledStudentIDs.add(studentID);
        }
    }

    public void updateAverageScore(double newScore) {
        // recompute average safely
        if (enrolledStudentIDs.size() == 0) return;

        averageCourseScore =
                ((averageCourseScore * (enrolledStudentIDs.size() - 1)) + newScore)
                        / enrolledStudentIDs.size();
    }

    public void incrementCompletions() {
        totalCompletions++;
    }

    public Lesson getLessonByID(String lessonID) {
        for (Lesson ls : lessons) {
            if (ls.getLessonID().equals(lessonID)) {
                return ls;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", instructorID='" + instructorID + '\'' +
                ", lessons=" + lessons.size() +
                ", enrolledStudents=" + enrolledStudentIDs.size() +
                '}';
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void removeLesson(String  lessonID,String courseID) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonID().equals(lessonID)) {
                lessons.remove(lesson);
            }
        }
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseID;
    private String title;
    private String description;
    private String instructorID;
    private List<Lesson> lessons;
    private List<Student> students;


    public Course(String courseID, String title, String description, String instructorID, List<Lesson> lessons, List<Student> students) {
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.instructorID = instructorID;
        this.lessons = lessons;
        this.students = students;
    }

    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson){
        this.lessons.remove(lesson);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}

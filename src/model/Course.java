package model;

import service.CourseService;
import service.LessonService;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseID;
    private String title;
    private String description;
    private String instructorID;
    private List<Lesson> lessons;
    private List<Student> students;
    private boolean pending;
    private boolean approved;
    private boolean rejected;


    public Course(String courseID, String title, String description, String instructorID) {
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.instructorID = instructorID;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
        this.pending = false;
        this.approved = false;
        this.rejected = false;
    }

    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
    }

    public void removeLesson(String lessonID,String courseID){
        CourseService courseService = new CourseService();
        LessonService lessonService = new LessonService(courseService);
        this.lessons.remove(lessonService.getLesson(lessonID,courseID));
    }

    public void addStudent(Student student){
        if(student == null){
            throw new IllegalArgumentException("Student cannot be null");
        }
        if(!students.contains(student)){
            this.students.add(student);
        }
    }

    public void removeStudent(Student student){
        this.students.remove(student);
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
        if(lessons==null){
            lessons = new ArrayList<>();
        }
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Student> getStudents() {
        if(students==null){
            students = new ArrayList<>();
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

}

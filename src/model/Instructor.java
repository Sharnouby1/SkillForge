package model;

import java.util.List;

public class Instructor extends User{

    //Attributes for instructor
    private List<Course> createdCourses;

    //Constructor
    public Instructor(String userId,  String role, String username, String email, String passwordHash, List<Course> createdCourses) {
        this.setUserId(userId);
        this.setRole(role);
        this.setUsername(username);
        this.setEmail(email);
        this.setPasswordHash(passwordHash);
        this.createdCourses = createdCourses;
    }

    //Getters & Setters
    public List<Course> getCreatedCourses() {
        return createdCourses;
    }

    public void setCreatedCourses(List<Course> createdCourses) {}

    //Methods
    public void CreateCourse(Course course){
        this.createdCourses.add(course);
    }
    public void RemoveCourse(Course course){
        this.createdCourses.remove(course);
    }
    public void EditCourse(Course course){}
    public void AddLesson(Lesson lesson){}
    public void EditLesson(Lesson lesson){}
    public void RemoveLesson(Lesson lesson){}
    public void ViewEnrolledStudents(){}


}

package service;

import model.Course;
import database.JsonDatabaseManager;

import java.util.List;

public class CourseService{

    JsonDatabaseManager databaseManager = new JsonDatabaseManager("users.json", "courses.json");
    private List<Course> courses = databaseManager.viewCourses();

    public Course createCourse(String courseID, String title, String description, String instructorID){
        Course course = new Course(courseID, title, description, instructorID);
        courses.add(course);
        databaseManager.addCourse(course);
        return course;
    }

    public Course getCourseByID(String courseID){
        for(Course course : courses){
            if(course.getCourseID().equals(courseID)){
                return course;
            }
        }
        return null;
    }

    public void deleteCourse(String courseID){
        courses.remove(getCourseByID(courseID));
    }
}

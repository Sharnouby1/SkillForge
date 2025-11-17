package service;

import model.Course;
import model.Lesson;
import model.Student;
import requirements.Validator;
import database.JsonDatabaseManager;

import java.util.List;

public class CourseService{

    JsonDatabaseManager databaseManager = new JsonDatabaseManager("users.json", "courses.json");
    private List<Course> courses = databaseManager.viewCourses();

    public Course createCourse(String courseID, String title, String description, String instructorID){
        if(Validator.validCourseID(courseID)){
            Course course = new Course(courseID, title, description, instructorID);
            courses.add(course);
            databaseManager.addCourse(course);
            return course;
        }
        return null;
    }

    public Course getCourseByID(String courseID){
        for(Course course : courses){
            if(course.getCourseID().equals(courseID)){
                return course;
            }
        }
        return null;
    }

    public void saveCourse(Course course){
        if(course == null){
            throw new IllegalArgumentException("Course cannot be null!");
        }
        databaseManager.editCourse(course);
    }

    public void editCourse(String courseID, String title, String description, String instructorID){
        Course course = getCourseByID(courseID);
        if(course == null){
            throw new NullPointerException("Course not found");
        }
        course.setTitle(title);
        course.setDescription(description);
        course.setInstructorID(instructorID);
        databaseManager.editCourse(course);
    }

    public void enrollStudent(String courseID, Student student){
        Course course = getCourseByID(courseID);
        if(course == null){
            throw new IllegalArgumentException("Course does not exist");
        }
        course.addStudent(student);
        student.getEnrolledCourses().add(course);
        databaseManager.editCourse(course);
    }

    public List<Student> getEnrolledStudents(String courseID){
        Course course = getCourseByID(courseID);
        if(course == null){
            throw new IllegalArgumentException("Course does not exist");
        }
        return course.getStudents();
    }

    public void addLesson(String courseID, Lesson lesson){
        Course course = getCourseByID(courseID);
        if(course == null){
            throw new IllegalArgumentException("Course does not exist");
        }
        course.addLesson(lesson);
        databaseManager.editCourse(course);
    }

    public void removeLesson(String courseID, Lesson lesson){
        Course course = getCourseByID(courseID);
        if(course == null){
            throw new IllegalArgumentException("Course does not exist");
        }
        course.removeLesson(lesson);
        databaseManager.editCourse(course);
    }

    public List<Lesson> getLessonsInCourse(String courseID){
        Course course = getCourseByID(courseID);
        if(course == null){
            throw new IllegalArgumentException("Course does not exist");
        }
        return course.getLessons();
    }
}

import model.*;
import database.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // Initialize Database Manager
        JsonDatabaseManager db = new JsonDatabaseManager("users.json", "courses.json");

        // -------------------------------
        // 1. ADD STUDENT
        // -------------------------------
        Student s1 = new Student(
                "S101",
                "student",
                "ahmed",
                "ahmed@gmail.com",
                "pass123",
                new ArrayList<>(),
                "0%"
        );

        db.addUser(s1);


        // -------------------------------
        // 2. ADD INSTRUCTOR
        // -------------------------------
        Instructor inst1 = new Instructor(
                "I201",
                "instructor",
                "mohamed",
                "mohamed@gmail.com",
                "pass456",
                new ArrayList<>()
        );

        db.addUser(inst1);


        // -------------------------------
        // 3. CREATE COURSE & ADD IT
        // -------------------------------
        Lesson l1 = new Lesson("L1", "Intro to Java", "Basics of java");
        Lesson l2 = new Lesson("L2", "OOP Concepts", "Classes, objects...");

        Course c1 = new Course(
                "C300",
                "Java Programming",
                "Learn Java from scratch",
                "I201",
                new ArrayList<>(Arrays.asList(l1, l2))
        );

        db.addCourse(c1);


        // -------------------------------
        // 4. VIEW STUDENTS
        // -------------------------------
        System.out.println("=== Students ===");
        for (User u : db.viewStudents()) {
            System.out.println(u.getUserId() + " - " + u.getUsername());
        }


        // -------------------------------
        // 5. VIEW INSTRUCTORS
        // -------------------------------
        System.out.println("\n=== Instructors ===");
        for (User u : db.viewInstructors()) {
            System.out.println(u.getUserId() + " - " + u.getUsername());
        }


        // -------------------------------
        // 6. VIEW COURSES
        // -------------------------------
        System.out.println("\n=== Courses ===");
        for (Course c : db.viewCourses()) {
            System.out.println(c.getCourseID() + " - " + c.getTitle());
        }


        // -------------------------------
        // 7. EDIT COURSE (UPDATE TITLE)
        // -------------------------------
        c1.setTitle("Java Programming - Updated Edition");
        db.editCourse(c1);

        System.out.println("\nCourse updated successfully!");
    }
}

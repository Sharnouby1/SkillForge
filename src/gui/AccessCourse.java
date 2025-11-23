package gui;

import javax.swing.*;
import database.*;
import model.Course;
import model.Student;
import service.AuthService;

import java.util.List;

public class AccessCourse extends JFrame {
    private JPanel container1;
    private JList<String>list1;
    public AccessCourse() {
        setTitle("Access Course");
        setContentPane(container1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        JsonDatabaseManager db = new JsonDatabaseManager("users.json", "courses.json");
        Student s = AuthService.getLoggedStudent();
        List<String> enrolledTitles = db.getEnrolledCourseTitles(s.getUserId());


        list1.setListData(enrolledTitles.toArray(new String[0]));


    }
}



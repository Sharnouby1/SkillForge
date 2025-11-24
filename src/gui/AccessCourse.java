package gui;

import javax.swing.*;
import database.*;
import model.*;
import service.AuthService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
//
public class AccessCourse extends JFrame {
    private JPanel container1;
    private JList<String>list1;
    private JButton accessButton;
    private JButton returnButton;

    public AccessCourse() {
        setTitle("Access Course");
        setContentPane(container1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        JsonDatabaseManager db = new JsonDatabaseManager("users.json", "courses.json");
        Student s = AuthService.getLoggedStudent();
        List<String> enrolledTitles = db.getEnrolledCourseTitles(s.getUserId());

        list1.setListData(enrolledTitles.toArray(new String[0]));


        accessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourseTitle = list1.getSelectedValue();
                if (selectedCourseTitle == null) {
                    JOptionPane.showMessageDialog(null, "Please select a course first!");
                } else {
                    // Get Course object from database
                    List<Course> allCourses = db.viewCourses();
                    Course selectedCourse = null;
                    for (Course c : allCourses) {
                        if (c.getTitle().equalsIgnoreCase(selectedCourseTitle)) {
                            selectedCourse = c;
                            break;
                        }
                    }

                    if (selectedCourse != null) {
                        JOptionPane.showMessageDialog(null, "Accessing Course: " + selectedCourse.getTitle());
                        dispose();
                        new Lessons(s, selectedCourse, db);
                    } else {
                        JOptionPane.showMessageDialog(null, "Course not found in database!");
                    }
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentMenu();
            }
        });
    }
}

//

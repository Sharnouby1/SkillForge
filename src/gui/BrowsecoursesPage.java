package gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Service.*;
import requirements.*;
import model.*;
import database.*;
public class BrowsecoursesPage extends JFrame {
    private JPanel Courses;
    private JComboBox course;
    private JButton backButton;
    private JButton EnrollButton;

    public BrowsecoursesPage() {
        setContentPane(Courses);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentMenu();
            }
        });

        EnrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Student s = AuthService.getLoggedStudent();

                if (s == null) {
                    JOptionPane.showMessageDialog(null, "No logged-in student found!");
                    return;
                }

                service.CourseService cs = new service.CourseService();
                String selectedTitle = course.getSelectedItem().toString();

                Course c = cs.getCourseByTitle(selectedTitle);

                if (c == null) {
                    JOptionPane.showMessageDialog(null, "Course not found!");
                    return;
                }

                cs.enrollStudent(c.getCourseID(), s);
                JOptionPane.showMessageDialog(null, "Course Enrolled Successfully!");
            }
        });

        course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}


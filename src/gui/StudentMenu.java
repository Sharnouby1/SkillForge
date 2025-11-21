package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMenu extends JFrame {
    private JPanel Container2;
    private JButton browseCoursesButton;
    private JButton enrollInCourseButton;
    private JButton accessLessonButton;
    private JButton trackProgressButton;
    private JLabel StudentText;

    public StudentMenu() {
        setTitle("Welcome");
        setContentPane(Container2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250,250);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        browseCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
new BrowsecoursesPage();

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
}

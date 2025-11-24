package gui;

import database.JsonDatabaseManager;
import model.Course;
import model.Lesson;

import javax.swing.*;
import java.util.ArrayList;

public class AdminDashboard extends JFrame {

    private JPanel mainPanel;
    private JComboBox<String> coursesCombo;
    private JTextArea courseTitle;
    private JTextArea description;
    private JComboBox<String> lessons;
    private JButton logoutButton;
    private JButton approveButton;
    private JButton declineButton;

    private ArrayList<Course> allCourses;
    private JsonDatabaseManager db;

    public AdminDashboard() {

        // GUI Designer initialization
        setContentPane(mainPanel);
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        // Load DB
        db = new JsonDatabaseManager("users.json", "courses.json");
        allCourses = db.viewCourses();

        // Load courses into combobox
        loadCoursesIntoCombo();

        // Listeners
        coursesCombo.addActionListener(e -> updateCourseDisplay());
        approveButton.addActionListener(e -> approveSelected());
        declineButton.addActionListener(e -> declineSelected());
        logoutButton.addActionListener(e -> {
            dispose();
            new RegisterFrame();
        });

        setVisible(true);
    }

    // Load courses into course combobox
    private void loadCoursesIntoCombo() {
        coursesCombo.removeAllItems();

        for (Course c : allCourses) {
            coursesCombo.addItem(c.getTitle());
        }

        if (coursesCombo.getItemCount() > 0) {
            coursesCombo.setSelectedIndex(0);
            updateCourseDisplay();
        }
    }

    // Update course details when selected
    private void updateCourseDisplay() {
        int index = coursesCombo.getSelectedIndex();
        if (index < 0) return;

        Course selected = allCourses.get(index);

        courseTitle.setText(selected.getTitle());
        description.setText(selected.getDescription());

        lessons.removeAllItems();
        for (Lesson l : selected.getLessons()) {
            lessons.addItem(l.getTitle());
        }
    }

    private void approveSelected() {
        int index = coursesCombo.getSelectedIndex();
        if (index < 0) return;

        Course selected = allCourses.get(index);

        selected.setApproved(true);
        selected.setPending(false);
        selected.setRejected(false);

        db.editCourse(selected);

        JOptionPane.showMessageDialog(mainPanel,
                "Course '" + selected.getTitle() + "' has been approved.");
    }

    private void declineSelected() {
        int index = coursesCombo.getSelectedIndex();
        if (index < 0) return;

        Course selected = allCourses.get(index);

        selected.setRejected(true);
        selected.setPending(false);
        selected.setApproved(false);

        db.editCourse(selected);

        JOptionPane.showMessageDialog(mainPanel,
                "Course '" + selected.getTitle() + "' has been declined.");
    }

}

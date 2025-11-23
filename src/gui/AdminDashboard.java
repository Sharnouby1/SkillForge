package gui;

import database.JsonDatabaseManager;
import model.Course;
import model.Lesson;

import javax.swing.*;
import java.util.ArrayList;

public class AdminDashboard {

    private JPanel mainPanel;
    private JComboBox<String> coursesCombo;     // first combo box (Pending Courses)
    private JTextArea courseTitle;              // title text area
    private JTextArea description;              // description text area
    private JComboBox<String> lessons;          // lessons combo box
    private JButton logoutButton;
    private JButton approveButton;
    private JButton declineButton;

    private ArrayList<Course> allCourses;        // loaded list of courses
    private JsonDatabaseManager db;

    public AdminDashboard() {

        db = new JsonDatabaseManager("users.json", "courses.json");

        allCourses = db.viewCourses();

        loadCoursesIntoCombo();

        coursesCombo.addActionListener(e -> updateCourseDisplay());

        approveButton.addActionListener(e -> approveSelected());

        declineButton.addActionListener(e -> declineSelected());

        logoutButton.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(logoutButton);
            RegisterFrame registerFrame = new RegisterFrame();
            registerFrame.setVisible(true);
            currentFrame.dispose();
        });
    }

    private void loadCoursesIntoCombo() {
        coursesCombo.removeAllItems();

        for (Course c : allCourses) {
            coursesCombo.addItem(c.getTitle());     // show title only
        }

        if (coursesCombo.getItemCount() > 0) {
            coursesCombo.setSelectedIndex(0);
            updateCourseDisplay();
        }
    }

    private void updateCourseDisplay() {
        int index = coursesCombo.getSelectedIndex();
        if (index < 0) return;

        Course selected = allCourses.get(index);

        courseTitle.setText(selected.getTitle());
        description.setText(selected.getDescription());

        lessons.removeAllItems();
        for (Lesson l : selected.getLessons()) {
            lessons.addItem(l.getTitle()); // only title for now
        }
    }

    private void approveSelected() {
        int index = coursesCombo.getSelectedIndex();
        if (index < 0) return;

        Course selected = allCourses.get(index);

        selected.setApproved(true);
        selected.setPending(false);
        selected.setRejected(false);

        db.updateCourse(selected);

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

        db.updateCourse(selected);

        JOptionPane.showMessageDialog(mainPanel,
                "Course '" + selected.getTitle() + "' has been declined.");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

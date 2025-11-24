package gui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import database.JsonDatabaseManager;
import model.Course;
import model.Lesson;

public class AdminDashboard extends JFrame {

    private JsonDatabaseManager dbManager;
    private JComboBox<String> coursesComboBox;
    private JTextArea courseDescriptionArea;
    private JComboBox<String> lessonsComboBox;
    private JButton logoutButton;
    private JButton approveButton;
    private JButton declineButton;

    private List<Course> pendingCourses;
    private Course selectedCourse;
    private JComboBox coursesCombo;
    private JPanel panel1;

    public AdminDashboard() {
        dbManager = new JsonDatabaseManager("users.json", "courses.json");
        initComponents();
        loadPendingCourses();
    }

    private void initComponents() {
        setTitle("Admin Dashboard - Course Approval");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Course Approval Dashboard");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
//        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        JPanel coursesPanel = new JPanel(new BorderLayout(5, 5));
        JLabel coursesLabel = new JLabel("Pending Courses:");
        coursesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        coursesComboBox = new JComboBox<>();
        coursesComboBox.addActionListener(e -> onCourseSelected());
        coursesPanel.add(coursesLabel, BorderLayout.NORTH);
        coursesPanel.add(coursesComboBox, BorderLayout.CENTER);
        mainPanel.add(coursesPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));


        JPanel descriptionPanel = new JPanel(new BorderLayout(5, 5));
        JLabel descriptionLabel = new JLabel("Course Description:");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        courseDescriptionArea = new JTextArea(8, 40);
        courseDescriptionArea.setLineWrap(true);
        courseDescriptionArea.setWrapStyleWord(true);
        courseDescriptionArea.setEditable(false);
        courseDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 12));
        courseDescriptionArea.setBackground(new Color(245, 245, 245));
        JScrollPane descScrollPane = new JScrollPane(courseDescriptionArea);
        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        descriptionPanel.add(descScrollPane, BorderLayout.CENTER);
        mainPanel.add(descriptionPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Lessons ComboBox Section
        JPanel lessonsPanel = new JPanel(new BorderLayout(5, 5));
        JLabel lessonsLabel = new JLabel("Course Lessons:");
        lessonsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lessonsComboBox = new JComboBox<>();
        lessonsPanel.add(lessonsLabel, BorderLayout.NORTH);
        lessonsPanel.add(lessonsComboBox, BorderLayout.CENTER);
        mainPanel.add(lessonsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        add(mainPanel, BorderLayout.CENTER);

        // Bottom Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(120, 35));
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.addActionListener(e -> logout());

        approveButton = new JButton("Approve");
        approveButton.setPreferredSize(new Dimension(120, 35));
        approveButton.setFont(new Font("Arial", Font.BOLD, 14));
//        approveButton.setBackground(new Color(76, 175, 80));
//        approveButton.setForeground(Color.WHITE);
        approveButton.setFocusPainted(false);
        approveButton.addActionListener(e -> approveCourse());

        declineButton = new JButton("Decline");
        declineButton.setPreferredSize(new Dimension(120, 35));
        declineButton.setFont(new Font("Arial", Font.BOLD, 14));
//        declineButton.setBackground(new Color(244, 67, 54));
//        declineButton.setForeground(Color.WHITE);
        declineButton.setFocusPainted(false);
        declineButton.addActionListener(e -> declineCourse());

        buttonsPanel.add(logoutButton);
        buttonsPanel.add(approveButton);
        buttonsPanel.add(declineButton);

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void loadPendingCourses() {
        pendingCourses = new ArrayList<>();
        List<Course> allCourses = dbManager.viewCourses();

        coursesComboBox.removeAllItems();
        coursesComboBox.addItem("-- Select a Course --");

        for (Course course : allCourses) {
            if ("pending".equalsIgnoreCase(course.getStatus())) {
                pendingCourses.add(course);
                coursesComboBox.addItem(course.getTitle());
            }
        }

        if (pendingCourses.isEmpty()) {
            courseDescriptionArea.setText("No pending courses available.");
            approveButton.setEnabled(false);
            declineButton.setEnabled(false);
        }
    }

    private void onCourseSelected() {
        int selectedIndex = coursesComboBox.getSelectedIndex();

        if (selectedIndex <= 0) {
            courseDescriptionArea.setText("");
            lessonsComboBox.removeAllItems();
            selectedCourse = null;
            approveButton.setEnabled(false);
            declineButton.setEnabled(false);
            return;
        }

        selectedCourse = pendingCourses.get(selectedIndex - 1);

        // Display course description
        courseDescriptionArea.setText(selectedCourse.getDescription());

        // Load lessons
        lessonsComboBox.removeAllItems();
        lessonsComboBox.addItem("-- Course Lessons --");

        List<Lesson> lessons = selectedCourse.getLessons();
        if (lessons != null && !lessons.isEmpty()) {
            for (Lesson lesson : lessons) {
                lessonsComboBox.addItem(lesson.getTitle());
            }
        } else {
            lessonsComboBox.addItem("No lessons available");
        }

        approveButton.setEnabled(true);
        declineButton.setEnabled(true);
    }

    private void approveCourse() {
        if (selectedCourse == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a course first.",
                    "No Course Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to approve the course:\n" + selectedCourse.getTitle() + "?",
                "Confirm Approval",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            selectedCourse.setStatus("approved");
            dbManager.editCourse(selectedCourse);

            JOptionPane.showMessageDialog(this,
                    "Course approved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            loadPendingCourses();
            courseDescriptionArea.setText("");
            lessonsComboBox.removeAllItems();
        }
    }

    private void declineCourse() {
        if (selectedCourse == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a course first.",
                    "No Course Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to decline the course:\n" + selectedCourse.getTitle() + "?",
                "Confirm Decline",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            selectedCourse.setStatus("declined");
            dbManager.editCourse(selectedCourse);

            JOptionPane.showMessageDialog(this,
                    "Course declined successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            loadPendingCourses();
            courseDescriptionArea.setText("");
            lessonsComboBox.removeAllItems();
        }
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            // Assuming you have a RegisterFrame class
            SwingUtilities.invokeLater(() -> {
                RegisterFrame registerFrame = new RegisterFrame();
                registerFrame.setVisible(true);
            });
        }
    }


    }

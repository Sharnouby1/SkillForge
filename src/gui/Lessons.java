package gui;

import database.JsonDatabaseManager;
import model.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Lessons extends JFrame {
    private JComboBox comboBox1;
    private JPanel container1;
    private JButton returnButton;
    public Lessons(Student student, Course course, JsonDatabaseManager db) {
        setTitle("Access Course");
        setContentPane(container1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox1.getSelectedIndex();
                Lesson selectedLesson = course.getLessons().get(selectedIndex);


                if (selectedIndex > 0) {
                    Lesson prevLesson = course.getLessons().get(selectedIndex - 1);
                    List<QuizResult> attempts = student.getQuizAttempts(prevLesson.getLessonID());

                    boolean passedPrev = false;
                    if (attempts != null && !attempts.isEmpty()) {
                        QuizResult lastAttempt = attempts.get(attempts.size() - 1);
                        passedPrev = lastAttempt.isPassed();
                    }
                    if (!passedPrev) {
                        JOptionPane.showMessageDialog(container1,
                                "You cannot access this lesson before successfully completing the previous lesson's quiz!",
                                "Access Denied",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(container1,
                        "Start The Lesson " + selectedLesson.getTitle(),
                        "Access Granted",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new content(selectedLesson);

            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccessCourse();
            }
        });
    }
}
//
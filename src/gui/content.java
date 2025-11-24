package gui;

import javax.swing.*;
import model.*;
import service.*;
public class content extends JFrame {
//
        private JPanel container1;
        private JLabel lessonTitle;
    private JTextArea lessonContent;
    private JButton takeQuizButton;

        private Lesson lesson;

        public content(Lesson lesson) {
            this.lesson = lesson;

            setContentPane(container1);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocationRelativeTo(null);


            lessonTitle.setText(lesson.getTitle());
            lessonContent.setText(lesson.getContent());
            lessonContent.setWrapStyleWord(true);
            lessonContent.setLineWrap(true);
            lessonContent.setEditable(false);

            takeQuizButton.addActionListener(e -> {
                if (lesson.getQuiz() == null) {
                    JOptionPane.showMessageDialog(this,
                            "This lesson has no quiz.",
                            "No Quiz",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Student student = AuthService.getLoggedStudent();
                new QuizFrame(lesson, student);
                dispose();
            });

            setVisible(true);
        }
    }

//
package gui;

import model.*;
import database.JsonDatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class QuizFrame extends JFrame {
    private JPanel container;
    private JButton submitButton;
    private List<ButtonGroup> groups; // لكل سؤال مجموعة أزرار
    private Lesson lesson;
    private Student student;

    public QuizFrame(Lesson lesson, Student student) {
        this.lesson = lesson;
        this.student = student;
        this.groups = new ArrayList<>();

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

//
        for (Question q : lesson.getQuiz().getQuestions()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createTitledBorder(q.getText()));

            ButtonGroup group = new ButtonGroup();
            for (String option : q.getOptions()) {
                JRadioButton btn = new JRadioButton(option);
                group.add(btn);
                panel.add(btn);
            }
            groups.add(group);
            container.add(panel);
        }

        submitButton = new JButton("Submit Quiz");
        submitButton.addActionListener(this::submitQuiz);
        container.add(submitButton);

        setContentPane(new JScrollPane(container));
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void submitQuiz(ActionEvent e) {
        List<String> answers = new ArrayList<>();
        Quiz quiz = lesson.getQuiz();


        for (ButtonGroup group : groups) {
            String answer = "";
            for (AbstractButton btn : java.util.Collections.list(group.getElements())) {
                if (btn.isSelected()) {
                    answer = btn.getText();
                    break;
                }
            }
            answers.add(answer);
        }


        QuizResult result = quiz.evaluateAttempt(answers);


        JsonDatabaseManager db = new JsonDatabaseManager("users.json", "courses.json");
        db.recordQuizAttempt(student.getUserId(), lesson.getLessonID(), quiz, result);

        // عرض النتائج مباشرة
        StringBuilder message = new StringBuilder();
        message.append("Score: ").append(result.getScore()).append("/").append(result.getTotalQuestions()).append("\n");
        message.append(result.isPassed() ? "Passed ✅" : "Failed ❌").append("\n\n");

        for (QuestionResult qr : result.getQuestionResults()) {
            message.append(qr.getQuestion().getText())
                    .append("\nYour Answer: ").append(qr.getUserAnswer())
                    .append(" | Correct: ").append(qr.getQuestion().getCorrectAnswer())
                    .append("\n\n");
        }

        JOptionPane.showMessageDialog(this, message.toString(), "Quiz Results", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}

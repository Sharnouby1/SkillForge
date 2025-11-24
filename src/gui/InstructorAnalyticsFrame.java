package gui;

import model.Course;
import service.CourseAnalytics;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InstructorAnalyticsFrame extends JFrame {
    private Course course;

    public InstructorAnalyticsFrame(Course course) {
        this.course = course;

        setTitle("Instructor Analytics of :" +course.getTitle());
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton btnPerformance = new JButton("Students Performance");
        JButton btnQuizAverages = new JButton("Quiz Averages per Lesson");
        JButton btnCompletion = new JButton("Completion Percentages");

        panel.add(btnPerformance);
        panel.add(btnQuizAverages);
        panel.add(btnCompletion);

        setVisible(true);
    }

    private void showPerformance() {
        CourseAnalytics analytics = new CourseAnalytics(course);
        List<Double> trend = analytics.getClassAverageTrend();

    }
}
